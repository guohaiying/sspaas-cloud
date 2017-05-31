package com.sspaas.sspaascloud.controller.user;


import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.Global;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.UserManager;
import com.sspaas.sspaascloud.kit.DateKit;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.kit.PasswordHashKit;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.kit.UUID22;
import com.sspaas.sspaascloud.service.user.IUserManagerService;



/**
 * 后台管理系统-用户管理Controller
 * 
 * 对后台用户的维护，添加修改
 * 对后台用户的权限管理
 * 
 * @author dongshaowei
 * 
 *
 */
@RequestMapping("/usermng")
@Controller
public class UserMngController extends BaseController {
	
	/**用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(UserMngController.class);

	@Autowired
	private IUserManagerService userService;
	

	UserManager userManager;
	JSONKit json= new JSONKit();
	String msg;
	
	/**
	 * 处理点击查询后台用户列表时跳转页面
	 * @author dongshaowei
	 *
	 * @return 后台用户列表
	 */
	@RequestMapping("/background/findAllUser")
	public String findAllUser(HttpSession httpSession, String menuId, HttpServletRequest request) {

		logger.debug("被拦截跳转到：查询所有的后台用户");
        UserManager user = (UserManager)httpSession.getAttribute(Global.USER_SESSION_KEY);
		return "/backstagemng/users-mng/alluser";
	}


	/**查找所有用户*/
	@RequestMapping("/background/select")
	public String findAllUser( int page , int rows,  HttpServletResponse response) {
		logger.debug("开始跳转到：findUserIdByStatis执行...");
		logger.debug("当前页是第"+page+"页"+"每页显示"+rows+"条");
		Integer totalRecord=userService.selectCount();
		logger.debug("共"+totalRecord+"页");
		PageCrt page1 = new PageCrt();
		page1.setPage(page);
		page1.setRows(rows);
		int index = ((page - 1) * rows);
		page1.setIndex(index);
		page1.setRecords(totalRecord);
		page1.setTotal(totalRecord % rows == 0 ? totalRecord / rows : totalRecord / rows + 1);
		logger.debug("开始执行数据库的分页查询操作"+"开始搜索第"+page1.getIndex()+"数据"+"每页显示"+rows+"条");
		List<Map> list = userService.selectPaging(page1);
		page1.setList(list);
		PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(page1, out);
		return  null ;

	}
	
	/** 跳转到添加用户页面 */
	@RequestMapping("/background/addUserToPage")
	public String addUserToPage() {
		return "/backstagemng/users-mng/adduser";
	}

	/** 登录 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, UserManager user, ModelMap model) {

		if (user==null || StrKit.isBlank(user.getLoginName()) ||  StrKit.isBlank(user.getPassword()) ||  StrKit.isBlank(user.getLoginCode())) {
			request.setAttribute("error", "有未输入的内容，请输入！");
			request.setAttribute("message", "有未输入的内容，请输入！");
			return "/login";
		}
		logger.info("正在请求登陆...");
		Object userToken = request.getSession().getAttribute(Global.USER_SESSION_KEY);
		if (userToken != null) {
			logger.info("用户已经登录");
			return "/main";
		}
		String loginCode = (String) httpSession.getAttribute("sysLoginCode");

		if(StrKit.isBlank(loginCode) || !loginCode.equalsIgnoreCase(user.getLoginCode())){
			request.setAttribute("error", "验证码错误，请重试！");
			logger.info("系统验证码是:"+loginCode+"  用户输入验证码："+user.getLoginCode()+"验证码错误");
			request.setAttribute("message", "验证码错误");
			logger.info("用户名:"+ user.getLoginName()+"密码："+user.getPassword());
			model.addAttribute("loginname", user.getLoginName());
			model.addAttribute("password", user.getPassword());
			return "/login";
		}

		//TODO  完成用户身份授权验证
		try {
			logger.debug("开始验证用户的合法性");
			userManager = userService.checkLogin(user);
			if (userManager == null) {
				logger.info("用户不存在");
				request.setAttribute("message", "用户不存在");
				return "/login";
			} else if (!PasswordHashKit.validatePassword(user.getPassword(), userManager.getPassword())){
				logger.info("密码错误");
				model.addAttribute("loginname", user.getLoginName());
				request.setAttribute("message", "密码错误");
				return "/login";
			}else{
				logger.info(userManager.getLoginName());
				userService.updateUser(userManager);
				userManager = userService.checkLogin(userManager);
				httpSession.setAttribute(Global.USER_SESSION_KEY, userManager);
				return "redirect:/lo";
		  }
		}catch (Exception e) {
		   e.printStackTrace();
		   logger.error("程序出现错误错误信息为"+e.getMessage());
		   request.setAttribute("message", "程序出现错误");
		 return "/login";

		}
	}

	/** 用户添加 */
	@RequestMapping("/background/addUser")
	public String addUser(UserManager user, ModelMap model) {
		System.out.println(user.getLoginName());
		String id = UUID22.getUUID22();
		user.setUserId(id);
		user.setCreatTime(DateKit.getStringTime("yyyy-MM-dd HH:mm:ss"));
		user.setCount(1); //初始注册，登陆次数为1
		user.setRoleId("3"); //1 超级管理员；2 企业管理员；3 普通用户
		logger.info("添加正在进行...");

		logger.debug("开始对密码进行加密存入数据库");
		try {
			user.setPassword(PasswordHashKit.createHash(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("密码加密时发生异常" + e.getMessage());
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			logger.error("密码加密时发生异常" + e.getMessage());
		}

		userService.saveUserManager(user);
		logger.info("请求登陆...");
		return "/message/addok";
	}

	/** 删除用户 */
	@RequestMapping("/background/delUser")
	public void  delectUser(UserManager user, HttpServletResponse response) {
		logger.info("删除正在进行...");
		userService.delectUser(user);

		PrintWriter out = super.getPrintWriter(response);
		json.objToJson("success", out);

	}

	/**修改用户密码跳转*/
	@RequestMapping("/background/updateUserPassword")
	public String updateUserPassword(){
		logger.debug("被拦截跳转到：updateusermessage页面");
		return "/backstagemng/users-mng/updateusermessage";
	}

	/** 修改密码*/
	@RequestMapping("/background/updateUserPass")
	public String updateUserPass(String newPassword, String userId){
		logger.debug("用户密码是"+newPassword+"用户Id是"+userId);
		logger.info("修改用户密码正在进行...");
		logger.debug("开始对密码进行加密存入数据库");
		UserManager userPassword=new UserManager();
		try {
			userPassword.setPassword(PasswordHashKit.createHash(newPassword));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("密码加密时发生异常" + e.getMessage());
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			logger.error("密码加密时发生异常" + e.getMessage());
		}
		userPassword.setUserId(userId);
		userService.updateUserPassword(userPassword);
		logger.info("请求登陆...");
		return "/message/ok";
	}

//	/** 个人中心 */
//	@RequestMapping("/finduserById")
//	public String preupdatetUser(UserManager user, ModelMap model) {
//		logger.info("查询正在进行...");
//		userManager = userService.findUserById(user);
//		model.addAttribute("user", userManager);
//		return "/user/updateusermessage";
//	}

	/** 更新用户 */
	@RequestMapping("/background/updateUser")
	public void updatetUser(UserManager user,HttpServletResponse response ) {
		logger.info("更新正在进行...");
		userService.updateUserById(user);

		PrintWriter out = super.getPrintWriter(response);
		json.objToJson("success", out);
	}

//	/** 分页查询 */
//	@RequestMapping("/paging")
//	public String showbypaging(Page page, ModelMap model) {
//		logger.info("正在进行分页...");
//		int count = userService.selectCount();
//		page.setRecords(count);
//		List<UserManager> list = userService.selectPaging(page);
//		model.addAttribute("userList", list);
//		return "/login";
//	}

	/**修改密码校验*/
	@RequestMapping("/checkpassword")
	public void checkPassword(UserManager user, String passWord, HttpServletResponse response) {

		logger.debug("开始进行密码校验:" + user.getUserId() + "..." + "前台密码是" + passWord);
		userManager = userService.findUserById(user);
		try {
			PrintWriter out;
			out = super.getPrintWriter(response);
			if (!PasswordHashKit.validatePassword(passWord, userManager.getPassword())) {
			 msg=json.getErrorMsg("no", "原密码错误！！");
				logger.info(msg);
				out.println(msg);
				out.flush();
				out.close();
			} else {
			msg=json.getErrorMsg("ok", "密码正确");
				logger.info(msg);
				out.println(msg);
				out.flush();
				out.close();
			}

		} catch (NoSuchAlgorithmException e) {
			logger.error("密码校验时发生异常" + e.getMessage());
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			logger.error("密码校验时发生异常" + e.getMessage());
			e.printStackTrace();
		}
	}

	/** 检查用户名唯一性 */
	@RequestMapping("/background/checkName")
	public void checkName(UserManager user,HttpServletResponse response ){

		logger.debug("开始检查用户名称是否存在，userName:"+user.getLoginName()+"...");
		PrintWriter out;
		try {
			boolean flag = userService.checkName(user);
			logger.debug("检查用户名称是否存在完毕，开始返回检查结果...");
			out = super.getPrintWriter(response);
			if(flag){
				 msg=json.getErrorMsg("ok", "登录名可以使用！！");
				//msg = "{\"ERR\":\""+"ok"+"\",\"msg\":\""+"登录名可以使用！！"+"\"}";
				 logger.info(msg);
				 out.println(msg);
				    out.flush();
				    out.close();
			}else{
				 msg=json.getErrorMsg("no", "登录名不可以使用！！");
			// msg = "{\"ERR\":\""+"no"+"\",\"msg\":\""+"登录名不可以使用！！"+"\"}";
				 logger.info(msg);
				 out.println(msg);
				    out.flush();
				    out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查用户登录名称是否存储发生异常，检查登录名："+user.getLoginName()+".ErrMessage："+e.getMessage());
		}

	}


	/**
	 * 登陆的用户点击退出，服务端销毁session ，跳转到登陆页
	 *
	 * @param httpSession
	 * 					：HttpSession
	 * @return 登陆页
	 */
	@RequestMapping("/background/exitLogin")
	public String exitLogin(HttpSession httpSession) {
		httpSession.invalidate();
		logger.debug("被拦截跳转到：登录页面");
		return "/login";
	}



//	/** 检查手机号是否已有 */
//	@RequestMapping("/checkPhone")
//	public void checkPhone(UserManager user, HttpServletResponse response){
//		System.out.println(user.getLoginName()+">>>!");
//		logger.info("请求登陆...");
//		boolean flag = userService.checkPhone(user);
//		  PrintWriter out;
//		out = super.getPrintWriter(response);
//		if(flag){
//			msg=json.getErrorMsg("ok", "手机号名可以使用！！");
//			// String errMsg = "{\"ERR\":\""+"ok"+"\",\"MSG\":\""+"手机号名可以使用！！"+"\"}";
//		    logger.info(msg);
//		    out.println(msg);
//		    out.flush();
//		    out.close();
//		}else{
//			msg=json.getErrorMsg("no", "手机号名不可以使用！！");
//// String errMsg = "{\"ERR\":\""+"no"+"\",\"MSG\":\""+"手机号不可以使用！！"+"\"}";
//			    logger.info(msg);
//			    out.println(msg);
//			    out.flush();
//			    out.close();
//		}
//
//
//	}
//	/** E-mail检查 */
//	@RequestMapping("/checkEmail")
//	@ResponseBody
//	public void checkEmail(UserManager user,HttpServletResponse response ){
//
//		logger.info("请求核查Email");
//		boolean flag = userService.checkEmail(user);
//		 PrintWriter out;
//		out = super.getPrintWriter(response);
//		if(flag){
//			msg=json.getErrorMsg("true", "E-mail可以使用！！");
//			// String errMsg = "{\"ERR\":\""+"true"+"\",\"MSG\":\""+"E-mail可以使用！！"+"\"}";
//		    logger.info(msg);
//		    out.println(msg);
//		    out.flush();
//		    out.close();
//		}else{
//			msg=json.getErrorMsg("false", "E-mail不可以使用！！");
//			 String errMsg = "{\"ERR\":\""+"false"+"\",\"MSG\":\""+"E-mail不可以使用！！"+"\"}";
//			    logger.info(errMsg);
//			    out.println(errMsg);
//			    out.flush();
//			    out.close();
//		}
//
//
//
//	}
//	/**姓名检查 */
//	@RequestMapping("/checkFullName")
//	@ResponseBody
//	public void checkFullName(UserManager user, HttpServletResponse response ) {
//		System.out.println(user.getLoginName()+">>>!");
//		logger.info("请求登陆...");
//		boolean flag = userService.checkFullName(user);
//		  PrintWriter out;
//		out = super.getPrintWriter(response);
//		if(flag){
//			msg=json.getErrorMsg("true", "姓名可以使用！！");
//			// String errMsg = "{\"ERR\":\""+"true"+"\",\"MSG\":\""+"姓名可以使用！！"+"\"}";
//		    logger.info(msg);
//		    out.println(msg);
//		    out.flush();
//		    out.close();
//		}else{
//			msg=json.getErrorMsg("false", "姓名不可以使用！！");
//			// String errMsg = "{\"ERR\":\""+"false"+"\",\"MSG\":\""+"姓名不可以使用！！"+"\"}";
//			    logger.info(msg);
//			    out.println(msg);
//			    out.flush();
//			    out.close();
//		}
//
//	}
//
//	public UserManager getUserManager() {
//		return userManager;
//	}
//
//	public void setUserManager(UserManager userManager) {
//		this.userManager = userManager;
//	}
//
//	public UserManager getUser() {
//		return user;
//	}
//
//	public void setUser(UserManager user) {
//		this.user = user;
//	}
//
//	public void setUserService(IUserManagerService userService) {
//		this.userService = userService;
//	}
	
}
