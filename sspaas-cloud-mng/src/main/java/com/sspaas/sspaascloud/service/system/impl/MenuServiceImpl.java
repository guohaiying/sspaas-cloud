package com.sspaas.sspaascloud.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.menu.MenuMapper;
import com.sspaas.sspaascloud.entity.Menu;
import com.sspaas.sspaascloud.kit.UUID22;
import com.sspaas.sspaascloud.service.system.IMenuService;
@Service("MenuService")
public class MenuServiceImpl implements IMenuService {

	Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Resource(name = "menuMapper")
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> findAllMenu() {
		return menuMapper.selectAll();
	}

	@Override
	public List<Menu> findUserButtonMenu(String roleId, String mid) {
		return menuMapper.findUserButtonMenu(roleId, mid);
	}

	@Override
	public Menu findIdMenu(String meid) {
		return menuMapper.selectByPrimaryKey(meid);
	}

	@Override
	public List<Menu> findMenu() {
		List<Menu> list = menuMapper.findMenu();
		return list;
	}

	@Override
	public void deleteMenu(List<String> menuList) {
		try {
			int result = menuMapper.deleteMenu(menuList);
			if (result < 1){
				logger.debug("删除出错！");
			}
		} catch (Exception e) {
			logger.debug("服务器繁忙！");
			e.printStackTrace();
		}

	}
//
	@Override
	public String addMenu(Menu menu) {
		String Msg = "";
		int result;
		String menuId = UUID22.getUUID22();
		String fatherId = menu.getId();

		if (menu.getMenuType() == 2){ //添加的类型为按钮的话
			List<String> id = menuMapper.selectMenu(fatherId);
			int idAn = menuMapper.selectMenuType(fatherId);
			if ((id.size() == 0 || id == null) && (idAn != 2)){ //id为空的话，可以添加按钮
				menu.setId(menuId);
				menu.setFatherId(fatherId);
				result = menuMapper.addMenu(menu);
				if (result < 1){
					logger.debug("添加按钮出错！");
					Msg = "{\"MSG\":\"" + "服务器繁忙！！" + "\"}";
				} else {
					Msg = "{\"MSG\":\"" + "添加按钮成功！！" + "\"}";
				}
			} else {
				Msg = "{\"MSG\":\"" + "该菜单下有其他菜单或者该菜单为按钮，不能添加按钮！！" + "\"}";
			}
		}

		if (menu.getMenuType() == 0){ //添加的类型为根目录的话
			menu.setId(menuId);
			menu.setFatherId("0");
			result = menuMapper.addMenu(menu);
			if (result < 1){
				logger.debug("添加根目录出错！");
				Msg = "{\"MSG\":\"" + "服务器繁忙！！" + "\"}";
			} else {
				Msg = "{\"MSG\":\"" + "添加根目录成功！！" + "\"}";
			}
		}

		if (menu.getMenuType() == 1){ //添加的类型为菜单的话
			int menuType = menuMapper.selectMenuType(fatherId); //查询该菜单的类型
			if(menuType != 2){
				menu.setId(menuId);
				menu.setFatherId(fatherId);
				result = menuMapper.addMenu(menu);
				if (result < 1){
					logger.debug("添加菜单出错！");
					Msg = "{\"MSG\":\"" + "服务器繁忙！！" + "\"}";
				} else {
					Msg = "{\"MSG\":\"" + "添加菜单成功！！" + "\"}";
				}
			} else {
				Msg = "{\"MSG\":\"" + "按钮下面不能添加菜单！！" + "\"}";
			}
		}

		return Msg;
	}

	@Override
	public String upMenu(Menu menu) {
		String Msg = "";
		int result;

		result = menuMapper.upMenu(menu);
		if (result < 1){
			logger.debug("修改菜单出错");
			Msg = "{\"MSG\":\"" + "服务器繁忙！！" + "\"}";
		}

		Msg = "{\"MSG\":\"" + "修改成功！！" + "\"}";
		return Msg;
	}
}
