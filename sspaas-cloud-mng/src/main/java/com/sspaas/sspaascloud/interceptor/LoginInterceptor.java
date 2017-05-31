package com.sspaas.sspaascloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sspaas.sspaascloud.entity.Global;
import com.sspaas.sspaascloud.kit.IpKit;


public class LoginInterceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("授权校验拦截器，截获请求，IP:["+IpKit.getIpAddr(request)+"]开始权限校验...");
		String uri = request.getRequestURI();
		logger.debug(uri);
		
		if ("/JoinYouMng".equals(uri)) {  
			logger.info("用户未登录，非法请求IP:"+request.getLocalAddr());
			response.sendRedirect("login");
			return false;
		}
		
		//验证用户是否登陆
		Object user = request.getSession().getAttribute(Global.USER_SESSION_KEY);
		if (user == null) {
			logger.info("用户未登录，非法请求IP:"+request.getLocalAddr());
			response.sendRedirect("login");
			return false;
		}
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	} 
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("afterCompletion");
	}

}
