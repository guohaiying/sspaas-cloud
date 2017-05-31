package com.sspaas.sspaascloud.service.system;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.Role;


public interface IRoleService {

    // 分页查询角色
    PageCrt findRole(PageCrt page);

    /**查询用户的角色id和角色名称*/
    Map selectRoleByUser(String userId);

    /**查询所有的角色id和角色名称*/
    List<Map> selectRole();

    Integer addRole(Role role);

    // 根据角色Id修改角色
    Integer updateRoleById(Role role);

    // 删除数据库中的角色
    Integer deleteRoleById(Role role);

    /*// 查询角色总数
    Integer total();*/

}
