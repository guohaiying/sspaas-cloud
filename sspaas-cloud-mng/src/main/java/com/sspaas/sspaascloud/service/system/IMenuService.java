package com.sspaas.sspaascloud.service.system;

import java.util.List;

import com.sspaas.sspaascloud.entity.Menu;



public interface IMenuService {
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Menu> findAllMenu();
	/**
	 * 根据ID查询菜单
	 */
    public Menu findIdMenu(String meid);

	/**添加菜单*/
	public String addMenu(Menu menu);

	/**查询菜单*/
	public List<Menu> findMenu();

	/**删除菜单*/
	public void deleteMenu(List<String> menuList);

	/**修改菜单*/
	public String upMenu(Menu menu);

	/**根据权限和菜单id查询按钮*/
	public List<Menu> findUserButtonMenu(String roleId, String mid);

}
