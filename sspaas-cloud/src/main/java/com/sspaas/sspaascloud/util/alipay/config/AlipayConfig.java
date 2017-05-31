package com.sspaas.sspaascloud.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *日期：2017-02-15
 *说明：
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.util.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088121745515979";
	//appid
	public static String app_id = "2017021005612233";
	// 商户的私钥
	public static String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDJ5DykdrOPy9ppmDP0Wci+IhMW24ZZ0nSfAEymZNHkEIHQFXaFK12pT9zLiDZ3piQM+eGT6ctGJUns3aZEGd56SrTrRiOg2VeMnL85arshB8K7//Mdrw68G8oBWllUBNpTSGZFOR6IC1M6WBWJEgYR6osOgsNpuUNW8XTOtpqPMzeXbOXeO4Scag65Aoevu3OYAmcCFRmlvulrb+g0u0hj+wEK5bS+c7a6ZjXrWQgeQVCK5rZdW6bVCk+MVHX1x4JyM4Y5LG6y7rBjqVUTu8OSkGhVE7sVM9V8gXwChfcpJNIOH8rpvzS1yRnm6oEPkgROtgggLD4Rku3TiSoSkda3AgMBAAECggEBAJcGbjrVNPOPmh49pjfW5i0dDkyeAT22Clla/vvGFaDWnFUNiTCLRTA/trpmdeBBX1x5nL5qOQ6LHGbdU+BDvcKbmgUZ3dMYYr6kDwQjjEJkLM834yTEbHBZFV63uHT6gFumoCKOXQGF/0d41vtHiVTjsAw103qGBRLH4wozXSFa6S7KUd0AuegaM9ubIU2An3d3eLZrUnvHmtbTWkiKs+wdqGErezuU0+GmU7oMmbR8ridFC3AxZZSNNGcvJgSARLNmswGlJ7Gk2pjXCI2WWzP13mFf5IBniAKmF+pzzkHadMYiMOyIaiHbPfL8c1xwaN4LY2idRUhMQ2cWZazdK/kCgYEA8XCfGRHuF0PyRjktBspADNBai/PA1cJhmdV78ZXi8Ss8YZh2Ej8HjiE+GYey2SdkwjUpyBYGVw+3fC+acOtCwoE+ixLlwBNhXJZf9oiAjPIpP4x0BoOJ9SPhCKViETd97h3KGJBSyAkh9FSqtyiYdcUlTy7LZEynmOA0+jGWoH0CgYEA1hEQFRpdnCurVqdzuazctzC5b8eEC59YM2Fjmv0Kqo7GwX/bXTguSbkY9d2qdMjtqMtObulvbAK9EbLGM38MHTXB9Nr8qovKMRK5H/D+pYKouQe2Z7/JrNOkWTBusnllD48RZ8R1cGrEZoORi4SGExNLe9+ljK6BHGwjB64LDkMCgYEAyRtzGlHKDU0GeCHV9Irw8Pt4AI0n+aWw4I9nbyLXZuUJpY12A9rGc0gTTvauLZ1Xm0DnLYfS/h/USTfpn9cGw7ceHn++KdC6z4FeK58l3ME1RH68glR8zFl3b1k6p3aeiYH8vZsxqPeadcc0b937qFVvr1YeCsyi6X8QAbreaY0CgYEAlCD+hfvWpwi46Z+6PtPVb5Zx212ui/RZrkRIDqnnRZrDAocdHuaPcUh6czhOib24Z0M4JRu3lIBYoPSanmvd0CCw9GTMzp8o7MhN/kJfk9xUMKWbnTNjtQpHAci38Rx7yEZx/uI9eesHuQB2KJstw+loJHHgNvdSNH7Hxrqyv80CgYA+mVWpy2ZD/Tao0uzunxI+x3Ofc6hYi2HTAv2rYYFCffWY3te1tsQT4s/8S+7eMS6BHt1vZzg+2qHQm/FtvpxAs9mkuv8qxkzgt4fmDU2BVBvaJEPbdLGWydd05nXlYUDIj+F7oZJGBH6pBTHAqr7LuAA/5y6npYZDrA11dd0ECw==";
	public static String md5key = "";
	public static String seller_email = "service@silversecuritytech.com";
	
	//支付宝网关
	public static String gateway = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝的公钥，无需修改该值
//	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	public static String ali_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgsNMya2aeTtdet6tGoZj0pvV4lvvKgNvkXwPWQoW+s8qLw0A5D0JPXLds5yL8wvfpP2FDyb83FWTIi2UCNw/3tIJ+ZXGGv/DwYs6Ht7/c6nLB+UGM11gszJD3+COYwnAW5ex7jx8wvGJ7RM1Fh/zIy2ythNK2TLWe4ZgjdhY0AWke/EtDpsHjqj5keuH7sG4XpatJLfGZCy6YR1Xp8NBVj4ndlHa4KguUgWnhPAT8fEufmd9+goQJlU5LF+GOcxW1P9YFvgH8N8jpJYzJ9R2nUyf5lH8k9+58TzzEoDfEMZWrjoFmsSLVLCkZiAddoGV+bzalxMtTrB2EVFJ7G0cFwIDAQAB";

	//支付宝回调公网域名
//	public static String notifyURL = "http://hiteamdong.in.8866.org:11248/sspaas-cloud/"; //本地测试
	public static String notifyURL = "http://cloud.docker.sspaas.net/sspaas-cloud/"; //服务器
	//支付宝回调接口路径
	public static String alipayCallback = "alipay/aliNotify";

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

	/*public static void initSetParam(String partner, String private_key, String ali_public_key, String log_path, String input_charset, String sign_type, String seller_email, String notifyURL, String md5key) {
		AlipayConfig.partner = partner;
		AlipayConfig.private_key = private_key;
		AlipayConfig.ali_public_key = ali_public_key;
		AlipayConfig.log_path = log_path;
		AlipayConfig.input_charset = input_charset;
		AlipayConfig.sign_type = sign_type;
		AlipayConfig.seller_email = seller_email;
		AlipayConfig.notifyURL = notifyURL;
		AlipayConfig.md5key = md5key;
	}*/

}
