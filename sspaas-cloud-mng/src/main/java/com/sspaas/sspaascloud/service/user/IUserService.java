package com.sspaas.sspaascloud.service.user;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface IUserService {

    /**
     * @method: appUserList
     * @Description: 查询app用户数据
     * @Author: 董绍威（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016年12月20日 下午2:49:30
     * @param pageCrt
     * @return PageCrt
     */
	public PageCrt appUserList(PageCrt pageCrt);
	
	/**
	 * @method: loginRecord
	 * @Description: 用户登录记录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月22日 上午11:30:19
	 * @param pageCrt
	 * @return: PageCrt
	 */
	public PageCrt loginRecord(PageCrt pageCrt);
	
	/**
	 * @method: editUser
	 * @Description: 账户冻结/封号
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月21日 下午4:06:00
	 * @param userId
	 * 							：用户id
	 * @param state
	 * 							：要修改成的状态
	 * @return: Integer
	 * 							：数据库受影响的行数
	 */
	public Integer editUser(int userId, byte state);
}
