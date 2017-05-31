package com.sspaas.sspaascloud.service.appmng.redis.colony;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

/**
 * Redis Service实现类
 * @Description: 
 * 			提供Redis的通用缓存数据访问更新
 * 
 * @Author: ghy（作者）
 */
@Service
public class ColonyRedis implements IColonyRedis {

	/**日志记录器*/
	Logger log = Logger.getLogger(ColonyRedis.class);
	
	@Autowired
	private JedisCluster jedisCluster;
    
	boolean result = false;
	
	
	/**
	 * 存储一个String类型数据并设置有效时间，单位秒
	 * @param key
	 * @param value
	 * @param seconds 秒
	 * @return
	 * @throws IOException 
	 */
	@Override
	public boolean putStrTime(String key,String value,int seconds) throws IOException{
        boolean isBroken = false;
        try {
        	jedisCluster.setex(key, seconds, value);
			return true;
		} catch (Exception e) {
			isBroken = true;
			return false;
		}finally {
			/*if (jedisCluster != null) {  
				jedisCluster.close();
            }  */
		}
	}

	@Override
	public long delete(String key) throws IOException {
		long count=0;
 		boolean isBroken = false;
         try {
             count = jedisCluster.del(key);
         }catch (Exception e) {
        	 e.printStackTrace();
             isBroken = true;
            log.error("Redis delete异常key:"+key+",ErrMsg:"+e.getMessage());
         }finally {
        	/* if (jedisCluster != null) {  
 				jedisCluster.close();
             }  */
         }
 	    return count;
	}

	@Override
	public String getStrByKey(String key) throws IOException {
		String result = null;
		boolean isBroken = false;
        try {
            result = jedisCluster.get(key);
        }catch (Exception e) {
            isBroken = true;
            log.error("Redis getStrByKey结合添加元素发生异常,key:"+key+",ErrMsg:"+e.getMessage());
        }finally {
			/*if (jedisCluster != null) {
				jedisCluster.close();
			}*/
        }
	    return result;
	}
	
	

}
