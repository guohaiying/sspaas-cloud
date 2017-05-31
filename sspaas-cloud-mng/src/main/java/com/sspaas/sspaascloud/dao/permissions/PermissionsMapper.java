package com.sspaas.sspaascloud.dao.permissions;

import java.util.List;

import com.sspaas.sspaascloud.entity.Permissions;

public interface PermissionsMapper {

    /**删除角色对应的菜单权限*/
    Integer deletePermissions(List<String> menuList);


    Integer deleteByRoleId(String roleId);

    Integer insertPermissions(Permissions record);
    //根据角色id查询权限
    List<String> getMenuRoleByRoleId(String roleId);

    List<Permissions> getMenuRoleByRoleIdList(String roleId);

    int deleteByPrimaryKey(String id);

    int insert(Permissions record);

    Permissions selectByPrimaryKey(String id);

    List<Permissions> selectAll();

    int updateByPrimaryKey(Permissions record);
}