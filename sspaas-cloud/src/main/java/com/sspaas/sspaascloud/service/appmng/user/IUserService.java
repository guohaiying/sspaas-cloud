package com.sspaas.sspaascloud.service.appmng.user;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.User;

public interface IUserService {
	
	/**
	 * @Name: 个人用户信息
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult getUserInfo(Map<String,Object> map);
	
	/**
	 * @Name: 修改用户信息
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult upUserInfo(Map<String,Object> map);
	
	/**
	 * @Name: 修改密码
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult upPassword(Map<String,Object> map);
	
	/**
	 * @Name: 修改头像
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult upUserIcon(MultipartFile file,Integer userId);
	
	
	/**
	 * @Name: registered
	 * @Description: 用户注册
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @param smsCode
	 * 				：短信验证码
	 * @return DataResult
	 */
	DataResult registered(User user, String smsCode);
	
	
	/**
	 * @Name: whetherRegistered
	 * @Description: 帐号是否注册
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @param smsCode
	 * 				：短信验证码
	 * @return DataResult
	 */
	DataResult whetherRegistered(User user);
	
	
	/**
	 * @Name: userCapacity
	 * @Description: 帐号是否注册
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @param smsCode
	 * 				：短信验证码
	 * @return DataResult
	 */
	DataResult userCapacity(Integer userId);
	
	/**
	 * @Name: login
	 * @Description: 用户登录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月5日
	 * @param user
	 * @param smsCode
	 * 				：短信验证码
	 * @param ip
	 * 				：客户端真实ip
	 * @param equipment
	 * 				： 设备（0 未知 1网页  2ios 3android）
	 * @return DataResult
	 */
	DataResult login(User user, String smsCode, String phoneUUID, String ip, Integer equipment);
	
	/**
	 * @Name: passwordBack
	 * @Description: 密码找回
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月6日
	 * @param user
	 * @param smsCode
	 * @return DataResult
	 * @throws IOException 
	 */
	DataResult passwordBack(User user, String smsCode) throws IOException;
	
	/**
	 * @Name: exit
	 * @Description: 用户退出系统
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月6日
	 * @param user
	 * @return DataResult
	 * @throws IOException 
	 */
	DataResult exit(User user) throws IOException;


	
}
