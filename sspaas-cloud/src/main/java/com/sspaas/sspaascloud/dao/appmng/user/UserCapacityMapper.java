package com.sspaas.sspaascloud.dao.appmng.user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sspaas.sspaascloud.entity.appmng.UserCapacity;

public interface UserCapacityMapper {
	
	/**
	 * @method: addUserCapacity
	 * @Description: 初始化用户总容量
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月19日 下午6:24:28
	 * @param record
	 * @return Integer
	 */
	Integer addUserCapacity(UserCapacity record);
	
	/**
	 * @method: updateUserCapacityByUserId
	 * @Description: 更新用户容量大小（增加容量）
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月17日 下午3:44:35
	 * @param userId
	 * 							：用户id
	 * @param capacityId
	 * 							：商品id
	 * @return: Integer
	 * 							：更新受影响行数
	 */
	Integer updateUserCapacityByUserId(Integer userId, Integer capacityId);
	
	/**
	 * @method: updateCapacityByUserId
	 * @Description: 更新用户总容量（减少容量）
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2017年2月20日 上午11:07:59
	 * @param userId
	 * 							：用户id
	 * @param capacityId
	 * 							：商品id
	 * @return: Integer
	 * 							：更新受影响行数
	 */
	Integer updateCapacityByUserId(Integer userId, Integer capacityId);
	
	/**
	 * @method: userCapacity
	 * @Description: 查询用户已用容量和总容量
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @param record
	 * @return Integer
	 */
	Map<String,Object> userCapacity(Integer userId);


	/**
	 * @Title: updateCapacity 
	 * @Description:更新用户已用容量
	 * @author: joker
	 * @Create:2017年2月8日下午3:00:55
	 * @param userId
	 * @param userdCapacity void 
	 */
	void updateCapacity(@Param("userId") Integer userId,@Param("userdCapacity") Float userdCapacity);
	
}