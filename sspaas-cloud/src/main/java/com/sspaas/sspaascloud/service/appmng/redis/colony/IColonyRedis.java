package com.sspaas.sspaascloud.service.appmng.redis.colony;

import java.io.IOException;

public interface IColonyRedis {
	
	/**
	 * @Name: 存String数据并指定有效期到Redis
	 * @Description: 
	 * @Author: ghy（作者）
	 * @param key
	 * 			： key
	 * @param value
	 * 			：缓存的值
	 * @param time
	 * 			：缓存有效期
	 * @throws IOException 
	 * @Return: String
	 */
	public boolean putStrTime(String key, String value, int time) throws IOException;
	
	/**
	 * @Name: 根据key获取String类型value
	 * @Author: ghy（作者）
	 * @param key
	 * 			redis中的key
	 * @throws IOException 
	 * @Return: String
	 * 				：返回key对应的value
	 */
	public String getStrByKey(String key) throws IOException;
	
	/**
	 * @Name: 删除元素
	 * @Author: ghy（作者）
	 * @param key
	 * 			Redis key
	 * @throws IOException 
	 * @Return: 返回删除记录条数
	 */
	public long delete(String key) throws IOException;

}
