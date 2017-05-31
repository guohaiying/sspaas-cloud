package com.sspaas.sspaascloud.controller.appmng.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.User;
import com.sspaas.sspaascloud.kit.IpKit;
import com.sspaas.sspaascloud.service.appmng.sms.ISMSService;
import com.sspaas.sspaascloud.service.appmng.user.IUserService;

@Controller
@RequestMapping("/user")
@SuppressWarnings("unchecked")
public class UserController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISMSService SMSService;

	/**
	 * @Name: 个人用户信息
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getUserInfo(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		DataResult result = new DataResult();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);

		log.debug("获取个人用户信息   参数说明：userId:" + map.get("userId"));

		result = userService.getUserInfo(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Name: 修改用户资料
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/upUserInfo", method = RequestMethod.POST)
	public ResponseEntity<DataResult> upUserInfo(@RequestBody Object o, HttpServletRequest request) {
		DataResult result = new DataResult();

		Map<String, Object> map = (Map<String, Object>) o;

		log.debug("修改用户信息   参数说明：userId:" + map.get("userId") + "  username:" + map.get("username") + "  email:"
				+ map.get("email") + "   age:" + map.get("age") + "  sex:" + map.get("sex"));

		result = userService.upUserInfo(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	/**
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @Name: 修改密码
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/upPassword", method = RequestMethod.POST)
	public ResponseEntity<DataResult> upPassword(@RequestBody Object o, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
		DataResult result = new DataResult();

		Map<String, Object> map = (Map<String, Object>) o;

		log.debug("修改密码   参数说明：userId:" + map.get("userId") + "  validcode:" + map.get("validcode") + "  password:"
				+ map.get("password")+" oldPassword:"+map.get("oldPassword"));

		result = userService.upPassword(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: sms
	 * @Description: 用户获取短信验证码
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月5日
	 * @param phone
	 * 				：用户手机号
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.GET)
	public ResponseEntity<DataResult> sms(Long phone, int type, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataResult result = SMSService.sendSMS(phone, type);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: 修改头像
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/upUserIcon", method = RequestMethod.POST)
	public ResponseEntity<DataResult> upUserIcon(Integer userId,@RequestParam MultipartFile file) {
		DataResult result = new DataResult();

		log.debug("修改头像   参数说明：userId:" + userId + "  file:" + file);

		result = userService.upUserIcon(file,userId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Name: registered
	 * @Description: 用户注册
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @param smsCode
	 * 				：用户短信验证码
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public ResponseEntity<DataResult> registered(@RequestBody Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = new User();
		user.setPhone((Long)map.get("phone"));
		user.setEmail((String)map.get("email"));
		user.setPassword((String)map.get("password"));
		String smsCode = (String)map.get("smsCode");
		
		log.debug("用户注册    参数说明：user:"+user+" smsCode:"+smsCode);
		
		DataResult result = userService.registered(user, smsCode);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	/**
	 * @Name: registered
	 * @Description: 查询账号是否注册过
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/whetherRegistered", method = RequestMethod.GET)
	public ResponseEntity<DataResult> whetherRegistered(Long phone,String email, HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = new User();
		user.setPhone(phone);
		user.setEmail(email);  
		
		DataResult result = userService.whetherRegistered(user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	/**
	 * @Name: userCapacity
	 * @Description: 查询用户已用容量和总容量
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/userCapacity", method = RequestMethod.GET)
	public ResponseEntity<DataResult> userCapacity(int userId, HttpServletRequest request,
			HttpServletResponse response) {
		
		DataResult result = userService.userCapacity(userId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	
	/**
	 * @Name: login
	 * @Description: 用户登录
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月2日
	 * @param user
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<DataResult> login(@RequestBody Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		
		/**用户登录：
		 * 		两种登陆方式：
		 * 				1：手机号和验证码登陆；
		 * 				2：手机号或邮箱和密码登陆；
		 * */
		User user = new User();
		user.setPhone((Long)map.get("phone"));
		user.setEmail((String)map.get("email"));
		user.setPassword((String)map.get("password"));
		String phoneUUID = (String)map.get("phoneUUID");
		String smsCode = (String)map.get("smsCode");
		Integer equipment = (Integer)map.get("equipment");// 设备（0 未知 1网页  2ios 3android）
		DataResult result = userService.login(user, smsCode, phoneUUID, IpKit.getIpAddr(request), equipment);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: passwordBack
	 * @Description: 用户密码找回
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月5日
	 * @param user
	 * @param smsCode
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 * @throws IOException 
	 */
	@RequestMapping(value = "/passwordBack", method = RequestMethod.POST)
	public ResponseEntity<DataResult> passwordBack(@RequestBody Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		/**用户密码找回：
		 * 		手机号、验证码、新密码
		 * */
		User user = new User();
		user.setPhone((Long)map.get("phone"));
		user.setPassword((String)map.get("password"));
		String smsCode = (String)map.get("smsCode");
		DataResult result = userService.passwordBack(user, smsCode);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: exit
	 * @Description: 用户退出系统
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月6日
	 * @param user
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.POST)
	public ResponseEntity<DataResult> exit(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		DataResult result = userService.exit(user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
