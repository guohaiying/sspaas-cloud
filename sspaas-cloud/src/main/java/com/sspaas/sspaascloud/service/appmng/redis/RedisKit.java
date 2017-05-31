package com.sspaas.sspaascloud.service.appmng.redis;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

/**
 * Redis Service实现类
 * @Description: 
 * 			提供Redis的通用缓存数据访问更新
 * 
 * @Author: ghy（作者）
 */
@Service
public class RedisKit implements IRedisKit {

	/**日志记录器*/
	Logger log = Logger.getLogger(RedisKit.class);
	
	@Autowired
	private Pool<Jedis> jedisPool;
    
	boolean result = false;
	
    Jedis jedis = null;
	
	private Jedis getJedis(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        }catch (JedisException e) {
        	log.error("获取Jedis链接失败："+e.getMessage());
            if (jedis != null) {
            	//程序报错，释放连接
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return jedis;
    }
	
	//回收资源
	protected void release(Jedis jedis, boolean isBroken){
        try {
			if (jedis != null){
			    if(isBroken){
			        jedisPool.returnBrokenResource(jedis);
			    }else{
			        jedisPool.returnResource(jedis);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("jedis 回收连接资源发生异常："+e.getMessage());
		}
    }
	
	 public long del(String... keys) {
         long count=0;
         boolean isBroken = false;
		try {
			Jedis jedis = getJedis();
			count = jedis.del(keys);
		} catch (Exception e) {
			e.printStackTrace();
			isBroken=true;
		}finally{
			release(jedis, isBroken);
		}
         return count;
     }
	
	 //回收资源异常 TODO
	@Override
	public String getStrByKey(String key) {
		Jedis jedis = null;
		String result = null;
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            result = jedis.get(key);
        }catch (Exception e) {
            isBroken = true;
            log.error("Redis getStrByKey结合添加元素发生异常,key:"+key+",ErrMsg:"+e.getMessage());
        }finally {
            release(jedis, isBroken);
        }
	    return result;
	}
	
	/**
	 * 存储一个String类型数据并设置有效时间，单位秒
	 * @param key
	 * @param value
	 * @param seconds 秒
	 * @return
	 */
	@Override
	public boolean putStrTime(String key,String value,int seconds){
		Jedis jedis = null;
        boolean isBroken = false;
        try {
			jedis=this.getJedis();
			jedis.setex(key, seconds, value);
			return true;
		} catch (Exception e) {
			isBroken = true;
			return false;
		}finally {
			release(jedis, isBroken);
		}
	}
	
	
	/**
	 * 操作redis列表(List),向列表头部插入数据
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean Lpush(String key,String value){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			long judge=jedis.lpush(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			isBroken=true;
			return false;
		}finally{
			release(jedis, isBroken);
		}
	}
	/**
	 * 返回列表（List）的长度
	 * @param key
	 * @return
	 */
	public long Llen(String key){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			long number=jedis.llen(key);
			return number;
		} catch (Exception e) {
			// TODO: handle exception
			isBroken=true;
			return 0;
		}finally{
			release(jedis, isBroken);
		}
	}
	/**
	 * 向redis集合(Set)中，添加一个元素
	 * @param key
	 * @param members
	 * @return
	 */
	@Override
	public boolean addToSet(String key,String members){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			jedis.sadd(key, members);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken=true;
			log.error("Redis Set结合添加元素发生异常,key:"+key+",members:"+members+",ErrMsg:"+e.getMessage());
			return false;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	
	@Override
	public boolean addToZSets(String key, String members, int score) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			jedis.zadd(key,score,members);
			return true;
		} catch (Exception e) {
			isBroken=true;
			log.error("Redis ZSet结合添加元素发生异常,key:"+key+",members:"+members+",score:"+score+",ErrMsg:"+e.getMessage());
			return false;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 获取set集合中的个数
	 * @param key
	 * @return
	 */
	@Override
	public long getSetObjNum(String key){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			long number=jedis.scard(key);
			return number;
		} catch (Exception e) {
			// TODO: handle exception
			isBroken=true;
			return 0;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 获取集合(Set)中的所有成员
	 * @param key
	 */
	@Override
	public Set<String> getSet(String key){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			Set<String> img=jedis.smembers(key);
			return img;
		} catch (Exception e) {
			isBroken=true;
			log.error("Redis ZSet结合添加元素发生异常,key:"+key+",ErrMsg:"+e.getMessage());
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 获取集合(ZSet)中的所有成员
	 * @param key
	 */
	@Override
	public Set<String> getZSet(String key){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			Set<String> img=jedis.zrange(key, 0, -1);
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			isBroken=true;
			log.error("Redis getZSet结合添加元素发生异常,key:"+key+",ErrMsg:"+e.getMessage());
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 获取set集合内指定个数的随机元素*/
	@Override
	public List<String> getRandomSet(String key, int count){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			List<String> RandomSet=jedis.srandmember(key, count);
			return RandomSet;
		} catch (Exception e) {
			isBroken=true;
			log.error("Redis 缓存String异常key:"+key+",count:"+count+",ErrMsg:"+e.getMessage());
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	
	/**
	 *   判断某一个元素是否为集合的元素*/
	@Override
	public boolean getKeySet(String key, String value){
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis=this.getJedis();
			isBroken = jedis.sismember(key, value);
			return isBroken;
		} catch (Exception e) {
			isBroken=true;
			return false;
		}finally{
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 移除Set集合中的成员
	 * @param key
	 * @param value
	 */
	@Override
	public boolean Srem(String key,String value){
		Jedis jedis = null;
		boolean isBroken = false;
		boolean result  = false;
		try {
			jedis=this.getJedis();
			jedis.srem(key, value);
			result = true;
		} catch (Exception e) {
			isBroken=true;
		}finally{
			release(jedis, isBroken);
		}
		return result;
	}
	
	/**
	 * 移除Set集合中的成员（有序）
	 * @param key
	 * @param value
	 */
	@Override
	public boolean ZSrem(String key,String value){
		Jedis jedis = null;
		boolean isBroken = false;
		boolean result  = false;
		try {
			jedis=this.getJedis();
			jedis.zrem(key, value);
			result = true;
		} catch (Exception e) {
			isBroken=true;
		}finally{
			release(jedis, isBroken);
		}
		return result;
	}
	
	@Override
	public boolean putStr(String key, String value) {
		boolean result = false;
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.set(key, value);
            result = true;
        }catch (Exception e) {
        	e.printStackTrace();
            isBroken = true;
            log.error("Redis 缓存String异常key:"+key+",value:"+value+",ErrMsg:"+e.getMessage());
        }finally {
            release(jedis, isBroken);
        }
	    return result;
	}
	
	@Override
	public boolean expire(String key,int seconds) {
		boolean result = false;
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.expire(key, seconds);
            result = true;
        }catch (Exception e) {
            isBroken = true;
        }finally {
            release(jedis, isBroken);
        }
	    return result;
	}
	
	@Override
	public long delete(String key) {
		long count=0;
 		boolean isBroken = false;
         try {
             jedis = this.getJedis();
             count = jedis.del(key);
         }catch (Exception e) {
        	 e.printStackTrace();
             isBroken = true;
            log.error("Redis delete异常key:"+key+",ErrMsg:"+e.getMessage());
         }finally {
             release(jedis, isBroken);
         }
 	    return count;
 	    
	}
	
	@Override
	public Set<String> getAllKeys(){
		Jedis jedis = getJedis();
		Set<String> s = jedis.keys("*");
		return s;
	}

	@Override
	public boolean saveMap(String key, Map<String, String> map) {
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hmset(key, map);
            result = true;
        }catch (Exception e) {
        	log.error("Redis 保存map失败，Message:"+e.getMessage());
        	isBroken = true;
        }finally {
            release(jedis, isBroken);
        }
	    return result;
	}
	
	@Override
	public boolean mapKeyRise(String key, String mapKey, int num) {
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hincrBy(key, mapKey, num);
            this.result = true;
        }catch (Exception e) {
        	log.error("Redis map 计数器改变失败，Message:"+e.getMessage());
        	isBroken = true;
        }finally {
            release(jedis, isBroken);
        }
	    return this.result;
	}
	
	@Override
	public List<String> getMap(String key) {
		boolean isBroken = false;
		List<String> result = null;
		try {
			jedis=this.getJedis();
			result = jedis.hvals(key);
			return result;
		} catch (Exception e) {
			isBroken=true;
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}

	@Override
	public String getValueByMapKey(String mapRedisKey, String mapKey) {
		boolean isBroken = false;
		String result = null;
		try {
			jedis=this.getJedis();
			result = jedis.hget(mapRedisKey, mapKey);
			return result;
		} catch (Exception e) {
			isBroken=true;
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}


	@Override
	public boolean delMap(String key, String value) {
		boolean isBroken = false;
        try {
            jedis = this.getJedis();
            jedis.hdel(key, value);
            result = true;
        }catch (Exception e) {
        	log.error("Redis 保存map失败，Message:"+e.getMessage());
        	isBroken = true;
        }finally {
            release(jedis, isBroken);
        }
	    return result;
	}
	
	
	@Override
	public  Map<String, String> getMapHgetall( String mapRedisKey) {
		boolean isBroken = false;
		 Map<String, String> result = null;
		try {
			jedis=this.getJedis();
			result = jedis.hgetAll(mapRedisKey);
			return result;
		} catch (Exception e) {
			isBroken=true;
			return null;
		}finally{
			release(jedis, isBroken);
		}
	}

}
