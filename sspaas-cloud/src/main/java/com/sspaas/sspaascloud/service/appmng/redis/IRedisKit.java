package com.sspaas.sspaascloud.service.appmng.redis;


import java.util.List;
import java.util.Map;
import java.util.Set;


public interface IRedisKit {
	
	/**
	 * @Name: 根据key获取String类型value
	 * @Author: ghy（作者）
	 * @param key
	 * 			redis中的key
	 * @Return: String
	 * 				：返回key对应的value
	 */
	public String getStrByKey(String key);
	
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
	 * @Return: String
	 */
	public boolean putStrTime(String key, String value, int time);
	
	/**
	 * @Name: 添加String 至 Redis缓存
	 * @Description: 
	 * @Author: ghy（作者）
	 * @param key
	 * @param value
	 * @return
	 * @Return: boolean
	 */
	public boolean putStr(String key, String value);
	
	
	/**
	 * @Name: 删除元素
	 * @Author: ghy（作者）
	 * @param key
	 * 			Redis key
	 * @Return: 返回删除记录条数
	 */
	public long delete(String key);
	
	/**
	 * @Name: 保存map对象到redis中
	 * @Description: 
	 * @Author: ghy（作者）
	 * @param key 
	 * @param map要保存的map
	 * @Return: boolean
	 */
	public boolean saveMap(String key, Map<String, String> map);
	
	/**
	 * @Name: map属性增长
	 * @Author: ghy（作者）
	 * @param key
	 * @param mapKey
	 * @param num
	 * @return
	 * @Return: boolean
	 */
	public boolean mapKeyRise(String key, String mapKey, int num);
	
	/**
	 * @Name: 获取map中指定的key对应的value
	 * @Author: ghy（作者）
	 * @param mapRedisKey
	 * 				map在redis中的key
	 * @param mapKey
	 * 				要获取map中的key值
	 * @Return: String
	 */
	public String getValueByMapKey(String mapRedisKey, String mapKey);
	
	/**
	 * @Name: 获取redis中map所有值
	 * @Author: ghy（作者）
	 * @param key
	 * @return
	 * @Return: Map<String,String>
	 */
	public List<String> getMap(String key);
	
	/**
	 * @Name: 获取所有的key
	 * @Description: 
	 * @Author: ghy（作者）
	 * @Return: Set<String> 返回set结合
	 */
	public Set<String> getAllKeys();
	
	/**
	 * @Name: 删除map中的某个键值 
	 * @Description: 
	 * @Author: ghy（作者）
	 * @Return: Set<String> 返回set结合
	 */
	public boolean delMap(String key, String value);
	
//	******************************************Redis Set 操作****************************************************
	
	/**
	 * @Name: 添加字符串到Set集合
	 * @Author: ghy（作者）
	 * @param key
	 * 				redis key
	 * @param members
	 * 				set集合元素
	 * @Return: boolean
	 * 				成功返回true，失败返回false
	 */
	public boolean addToSet(String key,String members);
	
	/**
	 * @Name: 添加字符串到Set有序集合
	 * @Author: ghy（作者）
	 * @param key
	 * 				redis key
	 * @param members
	 * 				set集合元素
	 * @Return: boolean
	 * 				成功返回true，失败返回false
	 */
	public boolean addToZSets(String key,String members, int score);
	
	
	/**
	 * @Name: 获取set集合元素个数
	 * @Author: ghy（作者）
	 * @param key
	 * 			set集合key
	 * @Return: 返回个数
	 */
	public long getSetObjNum(String key);
	
	/**
	 * @Name: 获取set集合中所有元素
	 * @Author: ghy（作者）
	 * @param key
	 * 				：redis key
	 * @Return: Set<String> 返回整个set集合
	 */
	public Set<String> getSet(String key);
	
	/**
	 * @Name: 获取set集合中所有元素(有序)
	 * @Author: ghy（作者）
	 * @param key
	 * 				：redis key
	 * @Return: Set<String> 返回整个set集合
	 */
	public Set<String> getZSet(String key);
	
	/**
	 * @Name: 获取集合内随机元素数
	 * @Author: ghy（作者）
	 * @param key   集合key
	 * @param count 获取元素数
	 * @Return: List<String>
	 */
	public List<String> getRandomSet(String key, int count);
	
	/**
	 * @Name: 判断某一个元素是否为集合的元素
	 * @Author: ghy（作者）
	 * @param key   集合key
	 * @param value 获取的值
	 * @Return: boolean
	 */
	public boolean getKeySet(String key, String value);
	
	/**
	 * 移除Set集合中的成员
	 * @param key
	 * @param value
	 */
	public boolean Srem(String key,String value);
	
	/**
	 * 移除Set集合中的成员(有序)
	 * @param key
	 * @param value
	 */
	boolean ZSrem(String key,String value);
	
	/**
	 * 设置key的有效时间
	 * @param key
	 * @param seconds
	 */
	public boolean expire(String key,int seconds);
	
	
	/**
	 * @Name: 返回名称为key的hash中所有的键（field）及其对应的value
	 * @Author: ghy（作者）
	 * @param mapRedisKey
	 * 				map在redis中的key
	 * @Return: Map
	 */
	public  Map<String, String> getMapHgetall( String mapRedisKey) ;
	
	
	
	
}
