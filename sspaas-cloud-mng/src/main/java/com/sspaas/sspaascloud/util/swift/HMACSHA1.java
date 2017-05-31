package com.sspaas.sspaascloud.util.swift;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Name:HMACSHA1
 * @Description: HMACSHA1 加密工具类
 * @author: joker
 * @Create:2016年12月20日下午3:48:57
 */
public class HMACSHA1 {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	/**
	 * @Title: HmacSHA1Encrypt 
	 * @Description:使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * @author: joker
	 * @Create:2016年12月20日下午3:48:10
	 * @param encryptText 被签名的字符串
	 * @param encryptKey 密钥
	 * @return
	 * @throws Exception byte[] 
	 */
	public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}

	/**
	 * @Title: byte2hex 
	 * @Description: 将二进制数字转换成字符串
	 * @author: joker
	 * @Create:2016年12月20日下午3:47:38
	 * @param b
	 * @return String 
	 */
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toLowerCase();
	}
}
