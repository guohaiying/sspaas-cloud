package com.sspaas.sspaascloud.service.appmng.sms;

import java.io.IOException;

import com.sspaas.sspaascloud.entity.appmng.DataResult;


public interface ISMSService {
	
	
	/**
	 * 发送短信服务
	 * @Name: sendSMS
	 * @Description: 
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年4月19日下午5:20:17
	 * @param phone
	 * 				：手机号
	 * @return
	 * @throws IOException 
	 * @Return: boolean
	 */
	public DataResult sendSMS(Long phone, int type) throws IOException;
	
	/**
	 * @Name: 校验短信验证码
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年4月21日下午6:21:26
	 * @param smsCode
	 * 				：用户输入的验证码
	 * @throws IOException 
	 * @Return: 1验证通过， 2验证码错误，3验证码超时
	 */
	public int checkSMSCode(String smsCode, String SMSKey) throws IOException;
	

}
