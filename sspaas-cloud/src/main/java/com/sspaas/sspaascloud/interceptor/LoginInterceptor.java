package com.sspaas.sspaascloud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sspaas.sspaascloud.kit.IpKit;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.service.appmng.user.IUserCacheService;

public class LoginInterceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Autowired
	private IUserCacheService userCacheService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("授权校验拦截器，截获请求，IP:[" + IpKit.getIpAddr(request) + "]开始权限校验...");
		String uri = request.getRequestURI();
		logger.debug(uri);
		String token = request.getHeader("token");
		String userId = request.getHeader("userId");

		logger.debug("用户请求API:" + uri + "参数  参数token:" + token + "   userId:" + userId);

		if (StrKit.isBlank(token)) {
			logger.error("用户请求API:" + uri + "参数为空,IP[" + IpKit.getIpAddr(request) + "]");

			JSONKit.outMsg(500, "请求数据不完整", response);
			return false;
		}

		String userToken = userCacheService.getUserToken(userId);

		if (!token.equals(userToken)) {
			logger.info(
					"令牌认证失败,userId:" + userId + ",Server Token:" + userToken + ",正确令牌:" + userToken + ",用户令牌:" + token);

			JSONKit.outMsg(501, "请重新登录", response);
			return false;
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("afterCompletion");
	}

}
