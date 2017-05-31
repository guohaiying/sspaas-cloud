package com.sspaas.sspaascloud.controller.appmng.pay;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.service.appmng.pay.IAlipayService;
import com.sspaas.sspaascloud.util.alipay.config.AlipayConfig;
import com.sspaas.sspaascloud.util.alipay.util.AlipayNotify;

/**
 * 	银联支付Controller
 * 
 *  1.	获取tn；
 *  2.	处理银联回调业务；
 * 
 * @author dongshaowei
 * @date 2017年3月7日 下午2:31:24
 * */

@Controller
@RequestMapping("/unionpay")
public class UnionpayController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(UnionpayController.class);

	@Autowired
	private IAlipayService alipayService;
	
	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;
	
	@Autowired
	private UserCapacityMapper userCapacityDAO;
	
	/**
	 * @method: aliPay
	 * @Description: 获取RSA签名
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月14日 下午5:41:57
	 * @param userId
	 * 							：当前用户id
	 * @param capacityId
	 * 							：商品id
	 * @param num
	 * 							：容量期限(天)
	 * @param totalFee
	 * 							：金额
	 * @param subject
	 * 							：标题
	 * @param body
	 * 							：内容
	 * @param req
	 * @param resp
	 * @return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/unionPay", method = RequestMethod.GET)
	public ResponseEntity<DataResult> aliPay(String userId, String capacityId, String num, Double totalFee, String subject, String body, 
			HttpServletRequest req, HttpServletResponse resp){
		
		String tradeNo = userId+"-"+capacityId+"-"+num+"-"+new Date().getTime()+(int)(Math.random()*1000); //userId+商品主键+商品期限+时间戳(冒号分隔)+100以内随机数
		
		DataResult result = alipayService.AliPay(tradeNo, totalFee, subject, body, AlipayConfig.alipayCallback);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @method: aliPayNotify
	 * @Description: 支付宝支付成功回调
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月14日 下午2:36:31
	 * @param req
	 * @param resp: String
	 */
	@RequestMapping(value = "/aliNotify", method = RequestMethod.POST)
	public String aliPayNotify(HttpServletRequest request, HttpServletResponse resp) {
        log.debug("支付宝回调");
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
//        String out_trade_no = request.getParameter("out_trade_no");// 商户订单号

        //商户订单号
//        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号
        try {
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			log.debug("本次交易号为：" + trade_no);
		} catch (UnsupportedEncodingException e1) {
			log.debug("获取交易号发生异常");
		}

        //交易状态
        String trade_status = "";
		try {
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.debug("获取交易状态发生异常");
			e.printStackTrace();
		}

        if(AlipayNotify.verify(params)){ //验证成功
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            	
            	//注意：
                //付款完成后，支付宝系统发送该交易状态通知
            	
            	String out_trade_no = params.get("out_trade_no"); //userId+商品主键+商品期限+时间戳(冒号分隔)
            	double total_fee = Double.parseDouble(params.get("total_amount")); //金额
            	String seller_id = params.get("seller_id"); //使用者id
//            	String partner = params.get("partner"); //使用者id
            	Integer userId = Integer.valueOf(out_trade_no.split("-")[0]); //用户id
            	Integer capacityId = Integer.valueOf(out_trade_no.split("-")[1]); //商品id
            	
            	UserPurchaseCapacity userPurchaseCapacity = userPurchaseCapacityDAO.selectOrderByNum(out_trade_no);
            	
            	if (userPurchaseCapacity == null){
           		 log.debug("该订单不存在");
                    return "fail";
            	}
            	
            	if (userPurchaseCapacity.getPayState() == 1){
            		log.debug("该订单已经支付完成");
//                    return "fail";
            		return "success";
            	}
            	
            	if (userPurchaseCapacity.getPrice() != total_fee){
           		 log.debug("订单支付金额不匹配");
                    return "fail";
            	}
            	
//            	if (!AlipayConfig.seller_email.equals(seller_email)){
//           		 log.debug("支付账户不匹配");
//                    return "fail";
//            	}
            	
            	if (!AlipayConfig.partner.equals(seller_id)){
           		 log.debug("支付宝合作伙伴id不匹配");
                    return "fail";
            	}
            	
            	int result = userPurchaseCapacityDAO.updateOrderByNum(out_trade_no, new Date().getTime()); //更新订单信息
            	if (result == 0){
            		log.debug("支付宝回调更新订单信息失败");
            		return "fail";
            	}
            	
            	int res = userCapacityDAO.updateUserCapacityByUserId(userId, capacityId); //更新用户容量大小
            	if (res == 0){
            		log.debug("支付宝回调更新用户容量大小失败");
            		return "fail";
            	}
                
            	log.debug("支付成功，响应支付宝...");
                return "success";
            }
        }else{//验证失败
            log.debug("+++++++++++++++++++=验证失败");
            return "fail";
        }
        log.debug("失败");
        return "fail";
    }
}
