package com.sspaas.sspaascloud.controller.system;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sspaas.sspaascloud.entity.Global;
import com.sspaas.sspaascloud.entity.Menu;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.Permissions;
import com.sspaas.sspaascloud.entity.Role;
import com.sspaas.sspaascloud.entity.UserManager;
import com.sspaas.sspaascloud.kit.DateKit;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.kit.UUID22;
import com.sspaas.sspaascloud.service.system.IMenuService;
import com.sspaas.sspaascloud.service.system.IPermissionsService;
import com.sspaas.sspaascloud.service.system.IRoleService;


/**
 * @author ghy
 *
 */
@RequestMapping("/role")
@Controller
public class RoleController {

	JSONKit json= new JSONKit();

	Logger logger = Logger.getLogger(RoleController.class);

	@Resource
	private IRoleService roleService;

    @Resource
    private IMenuService menuService;

	@Resource
	private IPermissionsService permissionsService;


	/** 左侧导航栏角色管理跳转链接 */
	@RequestMapping("/roleSys")
	public String roleSys(HttpSession httpSession, String menuId, HttpServletRequest request) {
        UserManager user = (UserManager)httpSession.getAttribute(Global.USER_SESSION_KEY);
        String roleId = user.getRoleId();
        List<Menu> listAll = menuService.findUserButtonMenu(roleId, menuId);
        String buttonJson = json.objToJson(listAll);
        request.setAttribute("mid", buttonJson);
		return "/backstagemng/users-mng/role";
	}


	/** 角色列表分页查询 */
	@RequestMapping("/roleList")
	public void roleList(HttpServletResponse response,PageCrt pageCrt) {
		PageCrt p =roleService.findRole(pageCrt);
		PrintWriter out = json.getPrintWriter(response);
		json.objToJson(p, out);
	}


	/** 角色添加 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public void addRole(Role role, HttpServletResponse response) {
		role.setRoleId(UUID22.getUUID22());
		role.setTime(DateKit.getDateTime("yyyy-MM-dd HH:mm:ss"));
		Integer i = roleService.addRole(role);
		PrintWriter out;
		if (i != null && i != 0) {
			out = json.getPrintWriter(response);
			json.objToJson(i, out);
		}
	}

	/** 角色修改 */
	@RequestMapping("/updateRole")
	public void updateRole(HttpServletResponse response,Role role) {
		Integer i = roleService.updateRoleById(role);
		PrintWriter out;
		out = json.getPrintWriter(response);
		if(i != null && i!= 0 ){
			json.objToJson("success", out);
		}
	}

	/** 角色删除 */
	@RequestMapping("/deleteRole")
	public void deleteRole(HttpServletResponse response,Role role) {
		Integer i = roleService.deleteRoleById(role);
		PrintWriter out;
		out = json.getPrintWriter(response);
		if(i != null && i!= 0 ){
			json.objToJson("success", out);
		}
	}

	/** 删除权限 */
	@RequestMapping("/deleteMenuRole")
	public void deleteMenuRole(HttpServletResponse response,String roleId) {
		Integer result = permissionsService.deleteByRoleId(roleId);
		PrintWriter out;
		out = json.getPrintWriter(response);
		if(result != null && result!= 0 ){
			json.objToJson("success", out);
		}
	}

	/** 添加权限 */
	@RequestMapping("/addMenuRole")
	public void addMenuRole(HttpServletResponse response, Permissions permissions) {
		permissions.setId(UUID22.getUUID22());
		Integer i = permissionsService.addMenuRole(permissions);
		PrintWriter out;
		if (i != null && i != 0) {
			out = json.getPrintWriter(response);
			json.objToJson(i, out);
		}
	}
}
