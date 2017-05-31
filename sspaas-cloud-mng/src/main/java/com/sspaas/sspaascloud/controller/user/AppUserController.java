package com.sspaas.sspaascloud.controller.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.user.IUserService;

/**
 * app用户管理系统-用户管理Controller
 * 
 * 对app用户进行封号，冻结
 * 
 * @author dongshaowei
 */
@RequestMapping("/appUser")
@Controller
public class AppUserController extends BaseController {
	
	/**app用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(AppUserController.class);
	
	@Autowired
	private IUserService userService;

	/**
	 * 处理点击查询app用户列表时跳转页面
	 * @author dongshaowei
	 * @return 后台用户列表
	 */
	@RequestMapping("/allappuser")
	public String allappuser() {

		logger.debug("跳转到：查询所有的app用户");
		return "/backstagemng/users-app/allappuser";
	}
	
	/**
	 * @method: userLoginRecord
	 * @Description: 查询用户登录记录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月22日 上午11:17:12
	 * @param httpSession
	 * @param request
	 * @return: String
	 */
	@RequestMapping("/userLoginRecord")
	public String userLoginRecord() {

		logger.debug("跳转到：查询用户登录记录");
		return "/backstagemng/users-app/userloginrecord";
	}
	
	/**
	 * @method: loginRecord
	 * @Description: 登录记录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月22日 上午11:26:31
	 * @param response
	 * @param pageCrt
	 * 							：页面请求参数
	 * @param username
	 * 							：用户昵称
	 * @param email
	 * 							：用户邮箱
	 * @param phone
	 * 							：用户手机号
	 */
	@RequestMapping("/loginRecord")
	public void loginRecord(HttpServletResponse response, PageCrt pageCrt, String username, String email, Long phone) {

		logger.debug("查询用户登录记录");
		pageCrt.getMap().put("username", username);
		pageCrt.getMap().put("email", email);
		pageCrt.getMap().put("phone", phone);
		PageCrt p =userService.loginRecord(pageCrt);
		PrintWriter out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	/**
	 * @method: appUserList
	 * @Description: 查询app用户数据
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月20日 下午2:25:37
	 * @param response
	 * @param pageCrt
	 * @Return: void
	 */
	@RequestMapping("/appUserList")
	public void appUserList(HttpServletResponse response, PageCrt pageCrt, String username, String email, Long phone) {

		logger.debug("请求所有的app用户数据");
		pageCrt.getMap().put("username", username);
		pageCrt.getMap().put("email", email);
		pageCrt.getMap().put("phone", phone);
		PageCrt p =userService.appUserList(pageCrt);
		PrintWriter out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	/**
	 * @method: editUser
	 * @Description: 账户冻结/封号
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月21日 下午4:02:15
	 * @param response
	 * @param userId
	 * 						：用户id
	 * @param state
	 * 						：要修改成的状态
	 */
	@RequestMapping("/editUser")
	public void editUser(HttpServletResponse response, int userId, byte state) {

		logger.debug("账户冻结/封号，userId："+userId+"要修改成："+state+"状态");
		Integer i =userService.editUser(userId, state);
		PrintWriter out = super.getPrintWriter(response);
		if(i != null && i!= 0){
			this.objToJson("success", out);
        }
	}
}
