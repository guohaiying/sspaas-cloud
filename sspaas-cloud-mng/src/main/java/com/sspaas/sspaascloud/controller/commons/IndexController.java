package com.sspaas.sspaascloud.controller.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.entity.Global;
import com.sspaas.sspaascloud.entity.UserManager;
import com.sspaas.sspaascloud.kit.JSONKit;

/**
 * 首页数据查询及跳转控制
 *
 */

@Controller
public class IndexController {
	
	Logger logger = Logger.getLogger(IndexController.class);

	UserManager userManager;
	JSONKit json= new JSONKit();
	String msg;
	
	
	@RequestMapping("/")
	public String toLoginPg(HttpServletRequest requset) {
		logger.debug("请求登陆页面。");
		return"/login";
	}
	
	@RequestMapping("/login")
	public String toLoginPage(HttpServletRequest requset) {
		logger.debug("请求登陆页面。");
		return"/login";
	}
    
	
	@RequestMapping("/lo")
	public String main2(HttpServletRequest request) {
		logger.debug("请求空路径，登陆返回首页，未登录返回登陆页面。");
		Object user = request.getSession().getAttribute(Global.USER_SESSION_KEY);
		if (user == null) {
			logger.info("用户未登录，非法请求IP:"+request.getLocalAddr());
			return"/login";
		}else{
			return "/main";
		}

	}

	@RequestMapping("/test")
	public String test() {
		logger.debug("被拦截跳转到：测试页面");
		return "/test";
	}
	
	@RequestMapping("/indexdate")
	public String index(HttpServletRequest request, HttpSession httpSession) {
		logger.debug("请求首页数据。");
		return"/index";
	}

}
