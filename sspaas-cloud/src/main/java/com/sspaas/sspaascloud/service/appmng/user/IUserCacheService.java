package com.sspaas.sspaascloud.service.appmng.user;

import java.io.IOException;

public interface IUserCacheService {
	
	
	/**
	 * @Name: 获取用户Token
	 * @Author: ghy（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年6月19日下午3:15:01
	 * @param userId
	 * @return
	 * @throws IOException 
	 * @Return: String
	 */
	public String getUserToken(String userId) throws IOException;
	
	
	/**
	 * @Name: 缓存用户Token
	 * @Author: ghy（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年6月19日下午3:15:01
	 * @param userId,token
	 * @return
	 * @throws IOException 
	 * @Return: boolean
	 */
	public boolean setUserToken(String userId,String token) throws IOException;
	
	
	/**
	 * @Name: 存String数据并指定有效期到Redis
	 * @Description: 
	 * @Author: ghy（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年4月19日下午7:05:41
	 * @param key
	 * 			： key
	 * @param value
	 * 			：缓存的值
	 * @param time
	 * 			：缓存有效期单位 秒
	 * @Return: String
	 */
	public boolean putStrTime(String key, String value, int time);
	
	/**
	 * @Name: 根据key获取String类型value
	 * @Author: ghy（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年4月19日上午11:27:41
	 * @param key
	 * 			redis中的key
	 * @throws IOException 
	 * @Return: String
	 * 				：返回key对应的value
	 */
	public String getStrByKey(String key) throws IOException;
}
