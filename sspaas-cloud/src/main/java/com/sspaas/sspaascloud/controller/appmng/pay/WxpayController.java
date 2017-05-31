package com.sspaas.sspaascloud.controller.appmng.pay;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.service.appmng.pay.IWxpayService;
import com.sspaas.sspaascloud.util.wxpay.config.WxpayConfig;
import com.sspaas.sspaascloud.util.wxpay.util.XMLUtil;

/**
 * 	微信支付Controller
 * 
 *  1.	获取MD5加密后的签名；
 *  2.	处理微信回调业务；
 * 
 * @author dongshaowei
 * @date 2017年2月14日 下午2:31:24
 * */

@Controller
@RequestMapping("/wxpay")
public class WxpayController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(WxpayController.class);

	@Autowired
	private IWxpayService wxpayService;
	
	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;
	
	@Autowired
	private UserCapacityMapper userCapacityDAO;
	
	@RequestMapping(value = "/wxPay", method = RequestMethod.GET)
	public ResponseEntity<DataResult> wxPay(String userId, String capacityId, String num, Integer actualPay, String body, 
			HttpServletRequest req, HttpServletResponse resp){
		
		String tradeNo = userId+"-"+capacityId+"-"+num+"-"+new Date().getTime()+(int)(Math.random()*1000); //userId+商品主键+商品期限+时间戳(冒号分隔)+100以内随机数
		DataResult result = wxpayService.generateOrderInfoByWeiXinPay(tradeNo, actualPay, body, req);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @method: wxPayNotify
	 * @Description: 微信支付成功回调
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月21日 下午5:22:59
	 * @param request
	 * @param resp
	 * @return: String
	 */
	@RequestMapping(value = "/wxNotify", method = RequestMethod.POST)
	public void wxPayNotify(HttpServletRequest request, HttpServletResponse resp, @RequestBody String xml,
			PrintWriter writer) {
		log.debug("微信支付回调");
		resp.resetBuffer();
		resp.setContentType("text/xml; charset=UTF-8");
		
		try {
			Map<String, String> map = XMLUtil.doXMLParse(xml);
			if ("SUCCESS".equals(map.get("return_code"))) {
				String out_trade_no = map.get("out_trade_no"); //userId+商品主键+商品期限+时间戳(冒号分隔)
            	double total_fee = Double.parseDouble(map.get("total_fee")); //金额
            	String mch_id = map.get("mch_id"); //微信商户账号
//            	String partner = params.get("partner"); //使用者id
            	Integer userId = Integer.valueOf(out_trade_no.split("-")[0]); //用户id
            	Integer capacityId = Integer.valueOf(out_trade_no.split("-")[1]); //商品id
            	
            	UserPurchaseCapacity userPurchaseCapacity = userPurchaseCapacityDAO.selectOrderByNum(out_trade_no);
            	
            	if (userPurchaseCapacity == null){
           		 	log.debug("该订单不存在");
	           		String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
					writer.print(returnXml);
					writer.flush();
            	}
            	
            	if (userPurchaseCapacity.getPayState() == 1){
            		log.debug("该订单已经支付完成");
            		String returnXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    				writer.print(returnXml);
    				writer.flush();
            	}
            	
            	if (userPurchaseCapacity.getPrice() != total_fee/100){
	           		log.debug("订单支付金额不匹配");
	           		String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
					writer.print(returnXml);
					writer.flush();
            	}
            	
            	if (!WxpayConfig.PARTNER.equals(mch_id)){
	            	log.debug("微信支付账户不匹配");
	           		String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
					writer.print(returnXml);
					writer.flush();
            	}
            	
            	int result = userPurchaseCapacityDAO.updateOrderByNum(out_trade_no, new Date().getTime()); //更新订单信息
            	if (result == 0){
            		log.debug("微信回调更新订单信息失败");
            		String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
					writer.print(returnXml);
					writer.flush();
            	}
            	
            	int res = userCapacityDAO.updateUserCapacityByUserId(userId, capacityId); //更新用户容量大小
            	if (res == 0){
            		log.debug("微信回调更新用户容量大小失败");
            		String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
					writer.print(returnXml);
					writer.flush();
            	}
                
            	log.debug("支付成功，响应微信...");
				
				String returnXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
				writer.print(returnXml);
				writer.flush();
				log.debug("成功返回微信");
			} else {
				String returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NO]]></return_msg></xml>";
				writer.print(returnXml);
				writer.flush();
				log.debug("微信支付回调处理不成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}