package com.sspaas.sspaascloud.controller.appmng.pay;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.util.wxpay.util.XMLUtil;

@Controller
@RequestMapping("/wxpay")
@SuppressWarnings("unchecked")
public class PayController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(PayController.class);
	
	

	/**
	 * @Name: 微信支付成功回调
	 * @Description: 
	 * @Return: void
	 */
	@RequestMapping(value = "/wxPayNotify", method = RequestMethod.POST)
	public void wxPayNotify(HttpServletRequest req, HttpServletResponse resp){
		try{
	        InputStream inStream = req.getInputStream();
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outSteam.write(buffer, 0, len);
	        }
	        outSteam.close();
	        inStream.close();
	        String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
	        Map<Object, Object> map = XMLUtil.doXMLParse(result);
	        for(Object keyValue : map.keySet()){
	        	log.info(keyValue+"="+map.get(keyValue));
	        }
	        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
	            //对数据库的操作 处理订单
	        	String orderId = map.get("out_trade_no").toString();
         		/*int  updResult =  payService.updatePayMessageByOrderId(orderId ,"");
         		if (updResult==1) {
         			log.info("微信支付成功回调订单id为"+orderId+"的状态为"+state+"业务服务器处理成功");
         			 resp.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK"));   //返回业务处理成功，不需要再次调用
         		}else{
         			log.error("数据库更新订单orderId【"+orderId+"】信息操作失败");
         			resp.getWriter().write(PayCommonUtil.setXML("ERROR", "error"));   //业务处理失败，需微信再次调用业务server
         		} */
	        }else{
	        	log.error("微信充值失败");
	        }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	
	
	
	
	
	
}
