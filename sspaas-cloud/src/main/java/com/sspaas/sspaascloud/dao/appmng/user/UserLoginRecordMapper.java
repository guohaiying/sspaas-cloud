package com.sspaas.sspaascloud.dao.appmng.user;

import com.sspaas.sspaascloud.entity.appmng.UserLoginRecord;

public interface UserLoginRecordMapper {
	
	/**
	 * @Name: insertUserLoginRecord
	 * @Description: 添加用户登录记录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param record
	 * @return Integer
	 */
	Integer insertUserLoginRecord(UserLoginRecord record);
	
	/**
	 * @Name: updateQuitTime
	 * @Description: 记录用户的退出时间
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param quitTime
	 * 				：系统当前时间
	 * @return Integer
	 */
	Integer updateQuitTime(Integer userId, Long quitTime);
	
	/**
	 * @Name: getUserUUId
	 * @Description: 获取用户最后一次登录uuid
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param userId
	 * 				：用户Id
	 * @return Integer
	 */
	String getUserUUId(Integer userId);
	
}