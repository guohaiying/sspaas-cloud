package com.sspaas.sspaascloud.dao.menu;

import java.util.List;

import com.sspaas.sspaascloud.entity.Menu;

public interface MenuMapper {

    /**查询菜单表的一级菜单的总数*/
    int selectCount();

    /**查询所有菜单*/
    List<Menu> findMenu();

    /**删除菜单*/
    int deleteMenu(List<String> menuList);

    /**查询指定父级id，并且不是按钮的菜单*/
    List<String> selectMenu(String fatherId);

    /**添加菜单*/
    int addMenu(Menu menu);

    /**查询菜单的类型（0根目录  1菜单  2按钮）*/
    int selectMenuType(String fatherId);

    /**修改菜单*/
    int upMenu(Menu menu);



    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    Menu selectByPrimaryKey(String id);

    List<Menu> selectAll();

    List<Menu> findUserButtonMenu(String roleId, String mid);

    int updateByPrimaryKey(Menu record);
}
