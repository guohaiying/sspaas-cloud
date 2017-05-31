package com.sspaas.sspaascloud.service.user;


import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.UserManager;


public interface IUserManagerService {

    /**查询用户是否存在*/
	public UserManager checkLogin(UserManager user);

    /**开始更新时间和登陆次数*/
    public void  updateUser(UserManager user);

    /**用户注册*/
    public void saveUserManager(UserManager user);

    /**检查用户名的唯一性*/
    boolean checkName(UserManager user);

    /**查询用户总条数*/
    public int selectCount();

    /**开始分页*/
    public List<Map> selectPaging(PageCrt page);

    /**编辑用户*/
    public void updateUserById (UserManager user);

    /**删除用户 */
    public void delectUser(UserManager user);

    /**修改用户密码*/
    public void updateUserPassword(UserManager user);

    /**修改密码校验*/
    public UserManager findUserById(UserManager user);
}
