package com.sspaas.sspaascloud.service.appmng.pay.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.JsonObject;
import com.sspaas.sspaascloud.controller.appmng.pay.PayController;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.kit.DateKit;
import com.sspaas.sspaascloud.service.appmng.pay.IAlipayService;
import com.sspaas.sspaascloud.util.alipay.config.AlipayConfig;
import com.sspaas.sspaascloud.util.alipay.util.SignUtils;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

@Service
public class AlipayService implements IAlipayService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(AlipayService.class);
	
	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;
	
	public static String sign(String content, String RSA_PRIVATE) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }
	
	@Override
	public DataResult AliPay(String trade_no, Double total_fee, String subject, String body, String notify_url) {
		
		log.debug("客户端开始请求获取签名：trade_no："+trade_no+"total_fee:"+total_fee+"subject:"+subject+"body:"+body+"notify_url:"+notify_url);
		
		DataResult result = new DataResult();
		
		UserPurchaseCapacity userPurchaseCapacity = new UserPurchaseCapacity();
		userPurchaseCapacity.setUserId(Integer.valueOf(trade_no.split("-")[0]));
		userPurchaseCapacity.setCapacityId(Integer.valueOf(trade_no.split("-")[1]));
		userPurchaseCapacity.setAddTime(new Date().getTime());
		userPurchaseCapacity.setMatureTime(DateKit.getNextDay(new Date(), Integer.valueOf(trade_no.split("-")[2])).getTime());
		userPurchaseCapacity.setPrice(total_fee);
		userPurchaseCapacity.setPayState((short)2); //1已付款 2待处理
		userPurchaseCapacity.setChannel((short)2); //1微信，2支付宝
		userPurchaseCapacity.setNote(body); //"支付宝手机快捷支付"
		userPurchaseCapacity.setOrderNumber(trade_no);
		int res = userPurchaseCapacityDAO.addUserCapacityOrder(userPurchaseCapacity); //获取签名之前生成订单
		if (res == 0){
			log.debug("生成订单失败");
			result.setStatusCode(500);
			result.setStatusMsg("生成订单失败");
			return result;
		}
		
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway,AlipayConfig.app_id,AlipayConfig.private_key,"json","utf-8",AlipayConfig.ali_public_key,"RSA2");
		AlipayTradeAppPayResponse response = null;
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setNotifyUrl(AlipayConfig.notifyURL + notify_url);
		request.setBizContent("{" + "\"timeout_express\":\"30m\"," + "\"seller_id\":" + "\"" + AlipayConfig.partner + "\"" + "," + "\"product_code\":\"QUICK_MSECURITY_PAY\"," +
				"\"total_amount\":" + "\"" + total_fee + "\"" + "," + "\"subject\":" + "\"" + subject + "\"" + "," + "\"body\":" + "\"" + body + "\"" + "," + "\"out_trade_no\":" + "\"" + trade_no + "\"" + "}");
		try {
			response = alipayClient.sdkExecute(request);
		} catch (AlipayApiException e) {
			log.debug("生成签名发生异常");
			e.printStackTrace();
		}
		
		log.debug("获取签名成功！");
        result.setStatusCode(DataResult.OK);
        result.setStatusMsg("获取签名结果成功");
		result.setDataList(response.getBody());
        
        return result;
	}
}
