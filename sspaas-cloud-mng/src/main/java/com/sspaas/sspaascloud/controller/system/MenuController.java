package com.sspaas.sspaascloud.controller.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.entity.Menu;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.system.IMenuService;
import com.sspaas.sspaascloud.service.system.IPermissionsService;


/**
 * @author ghy
 *
 */
@RequestMapping("/menu")
@Controller
public class MenuController {

	JSONKit json= new JSONKit();

	Logger logger = Logger.getLogger(MenuController.class);

	@Resource
	private IMenuService menuService;

    @Resource
    private IPermissionsService permissionsService;


	/** 查询菜单列表 */
	@RequestMapping("/menuList")
	public void menuList(HttpServletResponse response) {
		List<Menu> menuList =menuService.findMenu();
		PrintWriter out = json.getPrintWriter(response);
		json.objToJson(menuList, out);
	}

	/** 删除菜单 */
	@RequestMapping("/deleteMenu")
	public void deleteMenu(HttpServletResponse response, HttpServletRequest request) {

		String[] str = request.getParameterValues("va");
		logger.debug("删除的菜单id为：" + str);
		String menuId = str[0];
		String[] menuStr = menuId.split(",");
		List<String> menuList = new ArrayList<String>();
		for (String menu : menuStr){
			menuList.add(menu);
		}
		menuService.deleteMenu(menuList);
        permissionsService.deletePermissions(menuList);
		PrintWriter out;
		out = json.getPrintWriter(response);
		String Msg = "{\"MSG\":\"" + "删除菜单成功！！" + "\"}";
		logger.info(Msg);
		out.println(Msg);
		out.flush();
		out.close();
	}

	/** 添加菜单 */
	@RequestMapping("/addMenu")
	public void addMenu(HttpServletResponse response, Menu menu) {

		logger.debug("开始添加菜单：" + menu);
		String Msg = menuService.addMenu(menu);
		PrintWriter out;
		out = json.getPrintWriter(response);
		logger.info(Msg);
		out.println(Msg);
		out.flush();
		out.close();
	}

	/** 修改菜单 */
	@RequestMapping("/upMenu")
	public void upMenu(HttpServletResponse response, Menu menu) {

		logger.debug("开始修改菜单：" + menu);
		String Msg = menuService.upMenu(menu);
		PrintWriter out;
		out = json.getPrintWriter(response);
		logger.info(Msg);
		out.println(Msg);
		out.flush();
		out.close();
	}


}
