package com.sspaas.sspaascloud.dao.role;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.Role;

public interface RoleMapper {

    // 分页查询角色
    List<Role> selectRoleByPage(PageCrt pageCrt);

    /**查询用户的角色id和角色名称*/
    Map selectRoleByUser(String userId);

    /**查询所有的角色id和角色名称*/
    List<Map> selectRole();

    Integer deleteByPrimaryKey(String roleId);

    Integer insertRole(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectAll();

    Integer updateByPrimaryKey(Role record);

    Integer total(PageCrt page);
}