package com.sspaas.sspaascloud.service.appmng.user.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.service.appmng.redis.RedisKeys;
import com.sspaas.sspaascloud.service.appmng.redis.colony.IColonyRedis;
import com.sspaas.sspaascloud.service.appmng.user.IUserCacheService;

@Service
public class UserCacheService implements IUserCacheService{

	/**日志记录器*/
	Logger log = Logger.getLogger(UserCacheService.class);
	
	/*@Autowired
	private  IRedisKit redisKit;*/
	
	@Autowired
	private IColonyRedis coredis;
	
	@Override
	public String getUserToken(String userId) throws IOException {
		String token = this.getStrByKey(RedisKeys.redisUserToken(userId));
		return token;
	}

	@Override
	public boolean setUserToken(String userId, String token) throws IOException {
//		boolean result = redisKit.putStr(RedisKeys.redisUserToken(userId), token);
		boolean result = coredis.putStrTime(RedisKeys.redisUserToken(userId), token, 60*60*24*3);
		if(!result){
			log.error("redis 缓存用户token失败,userId:"+userId+"  token:"+token);
		}
		return result;
	}
	
	
	@Override
	public boolean putStrTime(String key, String value, int time) {
		try {
			boolean result =  coredis.putStrTime(key, value, time);
			if(!result){
				log.error("缓存String并设置存活时间发生异常 cacheKey："+key+",cacheValue:"+value+", time:"+time);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("缓存String并设置存活时间发生异常 cacheKey："+key+",cacheValue:"+value+", time:"+time+", ErrMsg:"+e.getMessage());
		}
		return false;
	}
	
	
	@Override
	public String getStrByKey(String key) throws IOException {
		return coredis.getStrByKey(key);
	}
	
}
