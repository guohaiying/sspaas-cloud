package com.sspaas.sspaascloud.service.appmng.sms.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.service.appmng.redis.RedisKeys;
import com.sspaas.sspaascloud.service.appmng.redis.colony.IColonyRedis;
import com.sspaas.sspaascloud.service.appmng.sms.ISMSSendService;
import com.sspaas.sspaascloud.service.appmng.sms.ISMSService;


/**
 * @Description: 
 * @Version: V1.00 （版本号）
 * @Create Date: 2016年4月19日下午5:26:55
 */
@Service
public class SMSService implements ISMSService {
	
	/**日志记录器*/
	Logger log = Logger.getLogger(SMSService.class);
			
	/*@Autowired
	private IRedisKit redis;*/
	
	@Autowired
	private IColonyRedis coredis;
	
	@Autowired
	private ISMSSendService SMSSendService;
	
	@Override
	public DataResult sendSMS(Long phone, int type) throws IOException {
		
		DataResult result = new DataResult();
		
		String code;//验证码
		
		switch (type) {
		case 1:
			//1注册
			code = StrKit.getCode();
			coredis.delete(RedisKeys.getRegister(phone));
			result = SMSSendService.sendSMS(phone,code,RedisKeys.getRegister(phone));
			log.info("发送【注册】短信,phone:"+phone+",CODE:"+code);
			break;
		case 2:
			//2登录
			code = StrKit.getCode();
			coredis.delete(RedisKeys.getLogin(phone));
			result = SMSSendService.sendSMS(phone,code,RedisKeys.getLogin(phone));
			log.info("发送【登录】短信,phone:"+phone+",CODE:"+code);
			break;
		case 3:
			//3密码找回
			code = StrKit.getCode();
			coredis.delete(RedisKeys.getPasswordBack(phone));
			result = SMSSendService.sendSMS(phone,code,RedisKeys.getPasswordBack(phone));
			log.info("发送【密码找回】短信,phone:"+phone+",CODE:"+code);
			break;
		case 4:
			//4修改密码
			code = StrKit.getCode();
			coredis.delete(RedisKeys.getUPPassword(phone));
			result = SMSSendService.sendSMS(phone,code,RedisKeys.getUPPassword(phone));
			log.info("发送【修改密码】短信,phone:"+phone+",CODE:"+code);
			break;
		default:
			break;
		}
		return result;
	}
	

	@Override
	public int checkSMSCode(String smsCode, String SMSKey) throws IOException {
		String code = coredis.getStrByKey(SMSKey);
		log.debug("正确验证码："+code+",用户验证码："+smsCode);
		if(StrKit.isBlank(code)){
			//3验证码超时
			return 3;
		}
		if(!smsCode.equals(code)){
			log.debug("正确验证码："+code+",用户提交的验证码："+smsCode);
			// 2验证码错误
			return 2;
		}
		//1验证通过
		return 1;
	}



	

}
