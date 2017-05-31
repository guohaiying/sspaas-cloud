package com.sspaas.sspaascloud.dao.user;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface UserMapper {
	
	/**
	 * @method: total
	 * @Description: 查询app用户总数
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月20日 下午3:43:56
	 * @return: Integer
	 */
	Integer total(PageCrt page);
	
	/**
	 * @method: selectAppUserList
	 * @Description: 分页查询app用户信息
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月20日 下午3:48:13
	 * @param page
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectAppUserList(PageCrt page);
	
	/**
	 * @method: editUser
	 * @Description: 修改用户状态
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月21日 下午4:08:48
	 * @param userId
	 * 						：用户id
	 * @param state
	 * 						：要修改成的状态值
	 * @return: Integer
	 * 						：数据库受影响的行数
	 */
	Integer editUser(int userId, byte state);
	
	/**
	 * @method: loginRecordTotal
	 * @Description: 登录记录总数
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月22日 上午11:40:26
	 * @param page
	 * @return: Integer
	 */
	Integer loginRecordTotal(PageCrt page);
	
	/**
	 * @method: selectLoginRecord
	 * @Description: 用户登录记录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月22日 上午11:43:04
	 * @param page
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> selectLoginRecord(PageCrt page);
}