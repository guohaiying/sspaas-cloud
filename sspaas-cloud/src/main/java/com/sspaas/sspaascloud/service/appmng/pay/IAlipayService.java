package com.sspaas.sspaascloud.service.appmng.pay;

import com.sspaas.sspaascloud.entity.appmng.DataResult;

public interface IAlipayService {
	
	/**
     * 支付宝支付
     * @param trade_no 订单号
     * @param total_fee 金额
     * @param subject 标题
     * @param body 内容
     * @param notify_url 回调地址
     * @param callback
     * @return
     */
	public DataResult AliPay(String trade_no, Double total_fee, String subject, String body, String notify_url);
	
}
