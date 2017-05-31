package com.sspaas.sspaascloud.dao.appmng.user;

import java.util.Map;

import com.sspaas.sspaascloud.entity.appmng.User;

public interface UserMapper {
	
	/**
	 * @Name: 个人用户信息
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	User getUserInfo(Map<String,Object> map);
	
	/**
	 * @Name: 修改用户信息
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	Integer upUserInfo(Map<String,Object> map);
	
	/**
	 * @Name: 修改密码
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	Integer upPassword(Map<String,Object> map);
	
	/**
	 * @Name: 修改头像
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	Integer upUserIcon(Map<String,Object> map);
	
	
	/**
	 * @Name: addUser
	 * @Description: 添加用户
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @return Integer
	 * 				：返回存入的用户主键id
	 */
	Integer addUser(User user);
	
	/**
	 * @Name: selectUserByP
	 * @Description: 根据手机号查询用户
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月5日
	 * @param phone
	 * @return User
	 */
	User selectUserByP(long phone);
	
	/**
	 * @Name: selectUserByE
	 * @Description: 根据邮箱查询用户
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月5日
	 * @param email
	 * @return User
	 */
	User selectUserByE(String email);
	
	/**
	 * @Name: passwordBack
	 * @Description: 根据用户手机号修改用户密码
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月6日
	 * @param user
	 * @return Integer
	 */
	Integer passwordBack(User user);
}