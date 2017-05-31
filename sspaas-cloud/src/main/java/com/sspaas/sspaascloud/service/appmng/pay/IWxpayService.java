package com.sspaas.sspaascloud.service.appmng.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sspaas.sspaascloud.entity.appmng.DataResult;

public interface IWxpayService {
	
	/**
	 * @method: generateOrderInfoByWeiXinPay
	 * @Description: 微信支付
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月21日 下午5:07:10
	 * @param orderId
	 * 									：订单编号
	 * @param actualPay
	 * 									：实际支付金额
	 * @param body
	 * 							：内容
	 * @return: DataResult
	 */
	public DataResult generateOrderInfoByWeiXinPay(String orderId, int actualPay, String body, HttpServletRequest request);
	
}
