package com.sspaas.sspaascloud.util.wxpay.config;

/* *
 *类名：WxpayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *日期：2017-02-21
 */

public class WxpayConfig {
	public static String APP_ID = "wx937e3da98549800c"; //微信开放平台审核通过的应用APPID
	public static String PARTNER = "1443882202"; //微信支付分配的商户号
	public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder"; //统一下单接口
	public static String PARTNER_KEY = "27bc9befbb156558478b6025d1326385"; //商户密钥
	
	//微信回调公网域名
//	public static String notify_url = "http://hiteamdong.in.8866.org:11248/sspaas-cloud/"; //本地测试
	public static String notify_url = "http://cloud.docker.sspaas.net/sspaas-cloud/"; //服务器
	//微信回调接口路径
	public static String wxpayCallback = "wxpay/wxNotify";
}
