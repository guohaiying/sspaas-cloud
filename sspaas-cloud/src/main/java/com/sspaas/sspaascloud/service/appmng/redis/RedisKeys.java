package com.sspaas.sspaascloud.service.appmng.redis;

public class RedisKeys {
	
	/**
     * 密码找回短信验证码缓存Redis的key
     * @param phone 手机号*/
    public static String getPasswordBack(Long phone){
    	return "PASBACK_"+phone;
    }
	
	/**
     * 登录短信验证码缓存Redis的key
     * @param phone 手机号*/
    public static String getLogin(Long phone){
    	return "LOGIN_"+phone;
    }
	
	/**
     * 注册短信验证码缓存Redis的key
     * @param phone 手机号*/
    public static String getRegister(Long phone){
    	return "REGISTER_"+phone;
    }
    
    /**
     * 修改密码验证码缓存Redis的key
     * @param phone 手机号*/
    public static String getUPPassword(Long phone){
    	return "UPPASSWORD_"+phone;
    }

	/**
     * 短信验证码缓存Redis的key
     * @param phone 手机号*/
    public static String getSMSKey(Long phone){
    	return "SMS_"+phone;
    }
    
    /**
     * @Name: 获取短信验证码缓存Redis的key
     * @param userId 用户id*/
    public static String redisUserToken(String userId){
    	return "userToken_"+userId;
    } 
    
    /**
     * @Name: 获取用户信息Redis key
     * 			缓存内容包含用户 id 用户头像url
     * @param userId
     * @Return: String
     */
    public static String redisUserIcon(String userId){
    	return "userIcon_"+userId;
    }
    
    /**
     * @Name: 获取用户信息Redis key
     * 			缓存内容包含用户 id 用户头像url
     * @param userId
     * @Return: String
     */
    public static String redisUserNickname(String userId){
    	return "userNickname_"+userId;
    }
    
    /**
     * @Name: 获取用户账号状态信息Redis key
     * 			缓存内容包含用户 id 用户头像url
     * @param userId
     * @Return: String
     */
    public static String redisUserTokenStatus(String userId){
    	return "user:tokenstatus:"+userId;
    }
    
}
