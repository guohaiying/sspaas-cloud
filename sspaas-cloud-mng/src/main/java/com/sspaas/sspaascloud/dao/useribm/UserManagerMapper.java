package com.sspaas.sspaascloud.dao.useribm;



import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.UserManager;


public interface UserManagerMapper {
    /**根据用户名查询用户*/
    UserManager select(UserManager user);

    /** 开始更新登录时间 */
    void updateUser(UserManager user);

    /**用户注册*/
    void saveUserManager(UserManager user);

    /** 检查用户名唯一性*/
    List<UserManager> checkName(UserManager user);

    /** 查询总数*/
    int selectTotalPage();

    /** 分页查询*/
    List<Map> selectePaging(PageCrt page);

    /**更新用户信息*/
    int updateByPrimaryKeySelective(UserManager record);

    /**删除用户*/
    int deleteByPrimaryKey(String userId);

    /**修改密码*/
    void updateUserPassword(UserManager user);

    /**根据用户id查询用户*/
    UserManager findUserById(String userId);

    int insert(UserManager record);

    UserManager selectByPrimaryKey(Integer userId);

    List<UserManager> selectAll();

    int updateByPrimaryKey(UserManager record);
}