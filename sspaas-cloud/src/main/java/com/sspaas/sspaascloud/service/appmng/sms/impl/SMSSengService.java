package com.sspaas.sspaascloud.service.appmng.sms.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.kit.HttpUtils;
import com.sspaas.sspaascloud.service.appmng.redis.colony.IColonyRedis;
import com.sspaas.sspaascloud.service.appmng.sms.ISMSSendService;

import net.sf.json.JSONObject;

/**
 * @Description: 
 * @Version: V1.00 （版本号）
 * @Create Date: 2016年4月19日下午5:26:55
 */
@Service
public class SMSSengService implements ISMSSendService {
	
	/**日志记录器*/
	Logger log = Logger.getLogger(SMSService.class);
	
	/*@Autowired
	private IRedisKit redis;*/
	
	@Autowired
	private IColonyRedis coredis;

	@Override
	public DataResult sendSMS(Long phone, String code, String SMSKey) throws IOException {

		DataResult result = new DataResult();
		
		String url = "http://sms.docker.sspaas.net/sspaas-message/sendShortMsg";
//		String url = "HTTP://sms.docker.sspaas.net/sspaas-message/sendMsg/"+phone+"/"+"90944";
		JSONObject json = new JSONObject();
		json.put("phone", phone);
		json.put("templet", "90944");
		json.put("phoneCode", code);
		log.debug("远程调用发送短信，手机号："+phone+",Code:"+code);
		JSONObject doPost = HttpUtils.doPost(url, json); //发送验证码
		log.debug("发送短信成功，手机号："+phone+",Code:"+code);
//		String doPost = HttpUtils.doGet(url);
		
		//判断是否成功
		if((Integer)doPost.get("statusCode") != 1){
			log.error("短信接口抽风，发送验证码短信失败，手机号："+phone+",Code:"+code);
			result.setStatusCode(500);
			result.setStatusMsg("短信服务繁忙");
			return result;
		}
		log.info("发送短信,phone:"+phone+",CODE:"+code);
		
		//将验证码加入缓存  时间为5分钟
		boolean res = coredis.putStrTime(SMSKey, code, 60*5);
		if(!res){
			log.error("短信发送成功redis缓存短信验证码失败");
		}
		result.setStatusCode(result.OK);
		result.setStatusMsg("短信获取成功");
		return result;
	}
			
}
