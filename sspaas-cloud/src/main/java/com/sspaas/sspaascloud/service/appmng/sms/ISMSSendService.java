package com.sspaas.sspaascloud.service.appmng.sms;

import java.io.IOException;

import com.sspaas.sspaascloud.entity.appmng.DataResult;


public interface ISMSSendService {
	
	
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
	public DataResult sendSMS(Long phone, String code, String SMSKey) throws IOException;
	
}
