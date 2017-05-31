package com.sspaas.sspaascloud.service.appmng.pay.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sspaas.sspaascloud.controller.appmng.pay.PayController;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.kit.DateKit;
import com.sspaas.sspaascloud.kit.IpKit;
import com.sspaas.sspaascloud.service.appmng.pay.IWxpayService;
import com.sspaas.sspaascloud.util.wxpay.config.WxpayConfig;
import com.sspaas.sspaascloud.util.wxpay.util.CommonUtil;
import com.sspaas.sspaascloud.util.wxpay.util.PayCommonUtil;
import com.sspaas.sspaascloud.util.wxpay.util.UUIDGen;
import com.sspaas.sspaascloud.util.wxpay.util.XMLUtil;

@Service
public class WxpayService implements IWxpayService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(WxpayService.class);
	
	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;

	//重点：封装参数调用统一下单接口，生成prepay_id(预支付订单Id)

	/**
	 * 微信支付
	 * @param orderId
	 * 							：订单编号
	 * @param actualPay
	 * 							：实际支付金额
	 * @param body
	 * 							：内容
	 * @return 
	 */
	public DataResult generateOrderInfoByWeiXinPay(String orderId, int actualPay, String body, HttpServletRequest request) {
			log.debug("客户端开始请求获取微信签名：orderId："+orderId+":actualPay:"+actualPay+":body"+body);
			DataResult res = new DataResult();
			
			UserPurchaseCapacity userPurchaseCapacity = new UserPurchaseCapacity();
			userPurchaseCapacity.setUserId(Integer.valueOf(orderId.split("-")[0]));
			userPurchaseCapacity.setCapacityId(Integer.valueOf(orderId.split("-")[1]));
			userPurchaseCapacity.setAddTime(new Date().getTime());
			userPurchaseCapacity.setMatureTime(DateKit.getNextDay(new Date(), Integer.valueOf(orderId.split("-")[2])).getTime());
			userPurchaseCapacity.setPrice((double)actualPay/100);
			userPurchaseCapacity.setPayState((short)2); //1已付款 2待处理
			userPurchaseCapacity.setChannel((short)1); //1微信，2支付宝
			userPurchaseCapacity.setNote(body); //"微信快捷支付"
			userPurchaseCapacity.setOrderNumber(orderId);
			int results = userPurchaseCapacityDAO.addUserCapacityOrder(userPurchaseCapacity); //获取签名之前生成订单
			if (results == 0){
				log.debug("生成订单失败");
				res.setStatusCode(500);
				res.setStatusMsg("生成订单失败");
				return res;
			}
		
		   String notify_url = WxpayConfig.notify_url+WxpayConfig.wxpayCallback;//回调地址
		   String uuid = UUIDGen.getUUID();
		   SortedMap<String, String> signParams = new TreeMap<String, String>();
		   signParams.put("appid", WxpayConfig.APP_ID);//app_id
		   signParams.put("body",body);//商品参数信息
		   signParams.put("mch_id", WxpayConfig.PARTNER);//微信商户账号
		   signParams.put("nonce_str", uuid);//32位不重复的编号
		   signParams.put("notify_url", notify_url);//回调页面
		   signParams.put("out_trade_no", orderId);//订单编号
//		   signParams.put("spbill_create_ip",request.getRemoteAddr() );//请求的实际ip地址
		   signParams.put("spbill_create_ip",IpKit.getIpAddr(request) );//请求的实际ip地址
		   signParams.put("total_fee", actualPay + "");//支付金额 单位为分
		   signParams.put("trade_type", "APP");//付款类型为APP
		   String sign = PayCommonUtil.createSign("UTF-8", signParams);//生成签名
		   signParams.put("sign", sign);
		   signParams.remove("key");//调用统一下单无需key（商户应用密钥）
		   String requestXml = PayCommonUtil.getRequestXml(signParams);//生成Xml格式的字符串
		   String result = CommonUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXml);//以post请求的方式调用统一下单接口
	
		   	//（注：ConstantUtil.UNIFIED_ORDER_URL=https://api.mch.weixin.qq.com/pay/unifiedorder;）
			//返回的result成功结果取出prepay_id：
		
			Map map = null;
			try {
				map = XMLUtil.doXMLParse(result);
			} catch (JDOMException e) {
				log.debug("解析xml出现异常");
				e.printStackTrace();
			} catch (IOException e) {
				log.debug("解析xml出现异常");
				e.printStackTrace();
			}
			String return_code=(String) map.get("return_code");
			String prepay_id =null;
			String returnSign=null;
			String returnNonce_str=null;
			if (return_code.contains("SUCCESS")){
			   prepay_id=(String) map.get("prepay_id");//获取到prepay_id
			}
//			StringBuffer weiXinVo=new StringBuffer();
			long currentTimeMillis = System.currentTimeMillis();//生成时间戳
			long second = currentTimeMillis / 1000L; //（转换成秒）
			String seconds = String.valueOf(second).substring(0, 10); //（截取前10位）
			SortedMap<String, String> signParam = new TreeMap<String, String>();
			signParam.put("appid", WxpayConfig.APP_ID);//app_id
			signParam.put("partnerid", WxpayConfig.PARTNER);//微信商户账号
			signParam.put("prepayid", prepay_id);//预付订单id
			signParam.put("package", "Sign=WXPay");//默认sign=WXPay
			signParam.put("noncestr", uuid);//自定义不重复的长度不长于32位
			signParam.put("timestamp",seconds);//北京时间时间戳
			String signAgain = PayCommonUtil.createSign("UTF-8", signParam);//再次生成签名
			signParam.put("sign", signAgain);
//			weiXinVo.append("appid=").append(WxpayConfig.APP_ID).append("&partnerid=").append(WxpayConfig.PARTNER).append("&prepayid=").append(prepay_id).append("&package=Sign=WXPay").append("&noncestr=").append(uuid).append("&timestamp=").append(seconds).append("&sign=").append(signAgain);//拼接参数返回给移动端

			log.debug("获取微信签名成功！");
	        res.setStatusCode(DataResult.OK);
	        res.setStatusMsg("获取签名结果成功");
//			res.setDataList(weiXinVo.toString());
	        res.setDataList(signParam);
	        
	        return res;
	}
}