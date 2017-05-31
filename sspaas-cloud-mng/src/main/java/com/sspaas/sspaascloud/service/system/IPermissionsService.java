package com.sspaas.sspaascloud.service.system;

import java.util.List;

import com.sspaas.sspaascloud.entity.Permissions;


public interface IPermissionsService {


    /**删除角色对应的菜单权限*/
    void deletePermissions(List<String> menuList);

    // 根据roleid删除用户权限
    Integer deleteByRoleId(String roleId);

    // 给角色授权
    Integer addMenuRole(Permissions permissions);

    //根据角色id查询权限
    List<String> getMenuRoleByRoleId(String roleId);

    //根据角色id查询权限
    List<Permissions> getMenuRoleByRoleIdList(String roleId);


}
