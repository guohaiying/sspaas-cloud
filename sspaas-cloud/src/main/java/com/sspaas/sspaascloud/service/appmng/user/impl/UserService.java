package com.sspaas.sspaascloud.service.appmng.user.impl;

import java.util.Map;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.sspaas.sspaascloud.dao.appmng.capacity.CapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserLoginRecordMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.User;
import com.sspaas.sspaascloud.entity.appmng.UserCapacity;
import com.sspaas.sspaascloud.entity.appmng.UserLoginRecord;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.kit.CommonKit;
import com.sspaas.sspaascloud.kit.PasswordHashKit;
import com.sspaas.sspaascloud.kit.RegExpValidatorUtils;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.service.appmng.redis.RedisKeys;
import com.sspaas.sspaascloud.service.appmng.redis.colony.IColonyRedis;
import com.sspaas.sspaascloud.service.appmng.sms.ISMSService;
import com.sspaas.sspaascloud.service.appmng.thread.user.CapacityThread;
import com.sspaas.sspaascloud.service.appmng.user.IUserCacheService;
import com.sspaas.sspaascloud.service.appmng.user.IUserService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

@Service
public class UserService implements IUserService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private UserMapper userDAO;

	@Autowired
	private SwiftUploadPojo uploadPojo;

	@Autowired
	private ISMSService SMSService;

	@Autowired
	private IUserCacheService userCacheService;

	/*@Autowired
	private IRedisKit redis;*/
	
	@Autowired
	private IColonyRedis coredis;

	@Autowired
	private CapacityMapper capacityDAO;

	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;

	@Autowired
	private UserCapacityMapper userCapacityDAO;

	@Autowired
	private UserLoginRecordMapper userLoginRecordDAO;

	/**
	 * @Name: 个人用户信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult getUserInfo(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {

			if (map == null || map.get("userId") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求个人信息参数为空  userId:" + map.get("userId"));
				return result;
			}

			User user = userDAO.getUserInfo(map);
			result.setDataList(user);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询个人信息发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 修改用户信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult upUserInfo(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {

			if (map == null || map.get("userId") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求修改资料参数为空  userId:" + map.get("userId"));
				return result;
			}

			// 验证用户邮箱
			if(!StrKit.isBlank(map.get("email")+"")){
				boolean isEmail = RegExpValidatorUtils.isEmail(map.get("email") + "");
				if (!isEmail) {
					result.setStatusCode(500);
					result.setStatusMsg("邮箱格式不正确");
					log.debug("邮箱格式不正确");
					return result;
				}
				
				//查询用户绑定的邮箱  
				User user = userDAO.getUserInfo(map);
				if(!StrKit.isBlank(user.getEmail())){
					if(!user.getEmail().equals(map.get("email")+"")){//如果前端传过来的和数据库的一致  说明修改邮箱
						User emailUser = userDAO.selectUserByE(map.get("email") + ""); // 根据邮箱查询用户
						if (emailUser != null) {
							result.setStatusCode(500);
							result.setStatusMsg("该邮箱已被占用");
							log.debug("用户请求修改资料    邮箱已经存在  email:" + map.get("email") + "");
							return result;
						}
					}
				}
			}

			Integer res = userDAO.upUserInfo(map);
			if (res > 0) {
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("修改成功");
			} else {
				result.setStatusCode(500);
				result.setStatusMsg("修改失败，请稍后重试");
			}
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("修改个人信息发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 修改密码
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult upPassword(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {

			if (map == null || map.get("userId") == null || map.get("oldPassword") == null
					|| map.get("validcode") == null || map.get("password") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求修改密码参数为空  userId:" + map.get("userId") + "  password:" + map.get("password")
						+ " validcode:" + map.get("validcode") + " oldPassword:" + map.get("oldPassword"));
				return result;
			}

			// 根据userId查询用户手机号
			User user = userDAO.getUserInfo(map);
			Long phone = user.getPhone();
			String oldpassword = user.getPassword();
			
			String pass = map.get("oldPassword")+"";
			if (!PasswordHashKit.validatePassword(pass,oldpassword)) {
				result.setStatusCode(500);
				result.setStatusMsg("密码错误");
				log.debug("密码错误");
				return result;
			}
			
			if(oldpassword.equals(map.get("password"))){
				result.setStatusCode(500);
				result.setStatusMsg("新密码和旧密码相同 ");
				log.debug("新密码和旧密码相同  旧密码："+oldpassword+" 新密码："+map.get("password"));
				return result;
			}

			// 验证短信验证码
			int checkResult = SMSService.checkSMSCode(map.get("validcode") + "", RedisKeys.getUPPassword(phone));
			if (checkResult == 3) {
				// 验证码超时
				result.setStatusCode(500);
				result.setStatusMsg("验证码超时");
				log.debug("验证码超时");
				return result;
			}
			if (checkResult == 2) {
				// 验证码错误
				result.setStatusCode(500);
				result.setStatusMsg("验证码错误");
				log.debug("验证码错误");
				return result;
			}

			// 将密码加密
			String password = PasswordHashKit.createHash(map.get("password") + "");
			map.put("password", password);

			Integer res = userDAO.upPassword(map);
			if (res > 0) {
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("请重新登录");
			} else {
				result.setStatusCode(500);
				result.setStatusMsg("修改失败，请稍后重试");
			}
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("修改个人信息发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 修改头像
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult upUserIcon(MultipartFile file, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		DataResult result = new DataResult();
		try {

			if (map == null || userId == null || file == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求修改头像参数为空  userId:" + userId + " file:" + file);
				return result;
			}

			// 先上传头像
			uploadPojo.setMultipartFile(file);
			uploadPojo.setContainerName("icon");
			String object = SwiftUploadUtils.createObject(uploadPojo);

			map.put("icon", object);
			map.put("userId", userId);

			// 修改用户头像地址到数据库中
			Integer res = userDAO.upUserIcon(map);
			if (res > 0) {
				map.clear();
				map.put("icon", object);
				result.setDataList(map);
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("修改成功");
			} else {
				result.setStatusCode(500);
				result.setStatusMsg("修改失败，请稍后重试");
			}
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("修改个人信息发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public DataResult registered(User user, String smsCode) {
		DataResult result = new DataResult();
		try {
			if (user == null || user.getPhone() == null || StrKit.isBlank(user.getPassword())
					|| user.getPassword().length() < 6 || smsCode == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求注册    请求参数不合法  user:" + user + "  phone:" + user.getPhone() + "  email:"
						+ user.getEmail() + " smsCode:" + smsCode);
				return result;
			}

			User userP = userDAO.selectUserByP(user.getPhone());
			if (userP != null) {
				result.setStatusCode(500);
				result.setStatusMsg("该用户已存在");
				log.debug("用户请求注册    用户已经存在  phone:" + user.getPhone());
				return result;
			}

			// 验证短信验证码
			int checkResult = SMSService.checkSMSCode(smsCode, RedisKeys.getRegister(user.getPhone()));
			if (checkResult == 3) {// 验证码超时
				result.setStatusCode(500);
				result.setStatusMsg("验证码超时");
				log.debug("验证码超时");
				return result;
			}
			if (checkResult == 2) {// 验证码错误
				result.setStatusCode(500);
				result.setStatusMsg("验证码错误");
				log.debug("验证码错误");
				return result;
			}

			if (user.getEmail() != null) {
				// 验证用户邮箱
				boolean isEmail = RegExpValidatorUtils.isEmail(user.getEmail());
				if (!isEmail) {
					result.setStatusCode(500);
					result.setStatusMsg("邮箱格式不正确");
					log.debug("邮箱格式不正确");
					return result;
				}

				User emailUser = userDAO.selectUserByE(user.getEmail()); // 根据邮箱查询用户
				if (emailUser != null) {
					result.setStatusCode(500);
					result.setStatusMsg("该邮箱已被占用");
					log.debug("用户请求注册    邮箱已经存在  email:" + user.getEmail());
					return result;
				}
			}
			String code = StrKit.getCodeLetter();
			user.setUsername(code);
			user.setState((byte) 1); // 1 正常 2冻结 3封号
			user.setRegisterTime(new Date().getTime());
			user.setSex((short) 0); // 性别 0 未知 1 男 2女
			user.setPassword(PasswordHashKit.createHash(user.getPassword()));// 密码加密
			user.setType((short) 1); // 用户身份 0未知 1普通用户 2 商家

			int res = userDAO.addUser(user);
			if (res == 0) {
				result.setStatusCode(500);
				result.setStatusMsg("注册失败，请稍后重试");
				log.debug("用户注册失败  phone:" + user.getPhone());
				return result;
			}

			// 初始化用户容量
			Map<String, Object> capacityMap = capacityDAO.selectIdByType();
			UserPurchaseCapacity userPurchaseCapacity = new UserPurchaseCapacity();
			userPurchaseCapacity.setAddTime(new Date().getTime());
			userPurchaseCapacity.setUserId(user.getUserId());
			userPurchaseCapacity.setCapacityId((Integer) capacityMap.get("capacityId"));
			userPurchaseCapacity.setPrice((double)0);
			userPurchaseCapacity.setState((short) 1); // 1正常 2到期
			userPurchaseCapacity.setNote("注册赠送");
			userPurchaseCapacityDAO.addUserPurchaseCapacity(userPurchaseCapacity);

			// 初始化用户总容量
			UserCapacity userCapacity = new UserCapacity();
			userCapacity.setTotalCapacity((Long) capacityMap.get("size")); // 总容量
			userCapacity.setUserdCapacity((long) 0);
			userCapacity.setUserId(user.getUserId());
			userCapacityDAO.addUserCapacity(userCapacity);

			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("注册成功");
			log.debug("注册成功");

		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("注册发生异常");
		}
		return result;
	}

	/**
	 * @Name: whetherRegistered
	 * @Description: 查询账号是否注册过
	 * @Author: 郭海英（作者）
	 * @Version: V1.00 （版本号）
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@Override
	public DataResult whetherRegistered(User user) {
		DataResult result = new DataResult();
		try {
			if (user == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户是否注册    请求参数不合法  user:" + user);
				return result;
			}

			if (user.getPhone() != null) {
				User userP = userDAO.selectUserByP(user.getPhone());
				if (userP != null) {
					result.setStatusCode(500);
					result.setStatusMsg("该用户已存在");
					log.debug("用户是否注册    用户已经存在  phone:" + user.getPhone());
					return result;
				}
			}

			if (user.getEmail() != null) {

				// 验证用户邮箱
				boolean isEmail = RegExpValidatorUtils.isEmail(user.getEmail());
				if (!isEmail) {
					result.setStatusCode(500);
					result.setStatusMsg("邮箱格式不正确");
					log.debug("邮箱格式不正确");
					return result;
				}

				User emailUser = userDAO.selectUserByE(user.getEmail()); // 根据邮箱查询用户
				if (emailUser != null) {
					result.setStatusCode(500);
					result.setStatusMsg("该邮箱已被占用");
					log.debug("用户是否注册    邮箱已经存在  email:" + user.getEmail());
					return result;
				}
			}

			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("帐号未被占用");

		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("注册发生异常");
		}
		return result;
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
	@Override
	public DataResult userCapacity(Integer userId) {
		DataResult result = new DataResult();
		try {

			if (userId == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("查询用户已用容量和总容量    请求参数不合法  userId:" + userId);
				return result;
			}

			Map<String, Object> map = userCapacityDAO.userCapacity(userId);

			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("查询成功");
			result.setDataList(map);

		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("注册发生异常");
		}
		return result;
	}

	@Override
	@SuppressWarnings("unused")
	public DataResult login(User user, String smsCode, String phoneUUID, String ip, Integer equipment) {
		DataResult result = new DataResult();
		try {
			String password = user.getPassword();
			Long phone = user.getPhone();
			String email = user.getEmail();
			if (user == null && phone == null && StrKit.isBlank(email) && StrKit.isBlank(password) && smsCode == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法!");
				log.debug("用户请求注册    请求参数不合法  user:" + user + "  phone:" + phone + "  email:" + email + " smsCode:"
						+ smsCode + "phoneUUID:" + phoneUUID);
				return result;
			}
			if (phoneUUID == null || equipment == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法!");
				log.debug("请求参数不合法!phoneUUID：" + phoneUUID + ",equipment：" + equipment);
				return result;
			}

			if (password != null) {
				if (phone != null) { // 手机号、密码登录-验证用户
					User u = userDAO.selectUserByP(phone);
					
					DataResult data = this.userCheck(u);// 验证用户的状态
					if (data != null) {
						return data;
					}
					
					if (u.getPassword() == null){ //自动注册用户没有密码
						result.setStatusCode(500);
						result.setStatusMsg("密码错误");
						log.debug("密码错误");
						return result;
					}

					if (!PasswordHashKit.validatePassword(password, u.getPassword())) {
						result.setStatusCode(500);
						result.setStatusMsg("密码错误");
						log.debug("密码错误");
						return result;
					}

					// 获取用户上一次登录的登录uuid
					String uuid = userLoginRecordDAO.getUserUUId(u.getUserId());

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", u.getUserId());

					// 判断缓存中是否存在用户token
					String token = userCacheService.getUserToken(u.getUserId() + "");
					if (StrKit.isBlank(token) || !uuid.equals(phoneUUID)) {
						// 生成新的token
						String newToken = CommonKit.getToken(System.currentTimeMillis() + "", phoneUUID,
								u.getUserId() + "");
						map.put("token", newToken);
						// 将token缓存到redis中
						boolean bool = userCacheService.setUserToken(u.getUserId() + "", newToken);
						if (!bool) {
							log.debug("将用户token缓存失败,token:" + newToken);
							result.setStatusCode(500);
							result.setStatusMsg("服务器繁忙");
							return result;
						}
					} else {
						map.put("token", token);
					}

					// 添加用户登录记录
					UserLoginRecord userLoginRecord = new UserLoginRecord();
					userLoginRecord.setUserId(u.getUserId());
					userLoginRecord.setLoginTime(new Date().getTime());
					userLoginRecord.setLoginIp(ip);
					userLoginRecord.setEquipment(equipment);
					userLoginRecord.setUuid(phoneUUID);
					userLoginRecordDAO.insertUserLoginRecord(userLoginRecord);
					
					// 用户登录 处理购买的容量是否到期
					new CapacityThread(u.getUserId(), userPurchaseCapacityDAO, userCapacityDAO).start();

					result.setDataList(map);

					result.setStatusCode(DataResult.OK);
					result.setStatusMsg("登录成功");
					log.debug("登录成功");
					return result;
				}
				if (email != null) { // 邮箱、密码登录-验证用户
					boolean isEmail = RegExpValidatorUtils.isEmail(user.getEmail()); // 验证用户邮箱
					if (!isEmail) {
						result.setStatusCode(500);
						result.setStatusMsg("邮箱输入不正确");
						log.debug("邮箱输入不正确 email:" + user.getEmail());
						return result;
					}

					User us = userDAO.selectUserByE(email);
					
					DataResult data = this.userCheck(us);// 验证用户的状态
					if (data != null) {
						return data;
					}
					
					if (us.getPassword() == null){ //自动注册用户没有密码
						result.setStatusCode(500);
						result.setStatusMsg("密码错误");
						log.debug("密码错误");
						return result;
					}

					if (!PasswordHashKit.validatePassword(password, us.getPassword())) {
						result.setStatusCode(500);
						result.setStatusMsg("密码错误");
						log.debug("密码错误");
						return result;
					}

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", us.getUserId());

					// 获取用户上一次登录的登录uuid
					String uuid = userLoginRecordDAO.getUserUUId(us.getUserId());

					// 判断缓存中是否存在用户token
					String token = userCacheService.getUserToken(us.getUserId() + "");
					if (StrKit.isBlank(token) || !uuid.equals(phoneUUID)) {
						// 生成新的token
						String newToken = CommonKit.getToken(System.currentTimeMillis() + "", phoneUUID,
								us.getUserId() + "");
						map.put("token", newToken);
						// 将token缓存到redis中
						boolean bool = userCacheService.setUserToken(us.getUserId() + "", newToken);
						if (!bool) {
							log.debug("将用户token缓存失败,token:" + newToken);
							result.setStatusCode(500);
							result.setStatusMsg("服务器繁忙");
							return result;
						}
					} else {
						map.put("token", token);
					}

					// 添加用户登录记录
					UserLoginRecord userLoginRecord = new UserLoginRecord();
					userLoginRecord.setUserId(us.getUserId());
					userLoginRecord.setLoginTime(new Date().getTime());
					userLoginRecord.setLoginIp(ip);
					userLoginRecord.setEquipment(equipment);
					userLoginRecord.setUuid(phoneUUID);
					userLoginRecordDAO.insertUserLoginRecord(userLoginRecord);
					
					// 用户登录 处理购买的容量是否到期
					new CapacityThread(us.getUserId(), userPurchaseCapacityDAO, userCapacityDAO).start();

					result.setDataList(map);

					result.setStatusCode(DataResult.OK);
					result.setStatusMsg("登录成功");
					log.debug("登录成功");
					return result;
				}
			}

			if (password == null) { // 手机号验证码登录
				if (smsCode == null || phone == null) {
					result.setStatusCode(500);
					result.setStatusMsg("请求参数不合法");
					log.debug("请求参数不合法,短信验证码：" + smsCode + "手机号：" + phone);
					return result;
				}
				
				// 验证短信验证码
				int checkResult = SMSService.checkSMSCode(smsCode, RedisKeys.getLogin(user.getPhone()));
				if (checkResult == 3) {
					// 验证码超时
					result.setStatusCode(500);
					result.setStatusMsg("验证码超时");
					log.debug("验证码超时");
					return result;
				}
				if (checkResult == 2 || checkResult != 1) {
					// 验证码错误
					result.setStatusCode(500);
					result.setStatusMsg("验证码错误");
					log.debug("验证码错误");
					return result;
				}

				User use = userDAO.selectUserByP(phone);
				
				if (use == null){ //手机号验证码登录，如果账户不存在，自动注册
					String code = StrKit.getCodeLetter();
					user.setUsername(code);
					user.setState((byte) 1); // 1 正常 2冻结 3封号
					user.setRegisterTime(new Date().getTime());
					user.setSex((short) 0); // 性别 0 未知 1 男 2女
					user.setType((short) 1); // 用户身份 0未知 1普通用户 2 商家

					int res = userDAO.addUser(user);
					if (res == 0) {
						result.setStatusCode(500);
						result.setStatusMsg("注册失败，请稍后重试");
						log.debug("用户注册失败  phone:" + user.getPhone());
						return result;
					}

					// 初始化用户容量
					Map<String, Object> capacityMap = capacityDAO.selectIdByType();
					UserPurchaseCapacity userPurchaseCapacity = new UserPurchaseCapacity();
					userPurchaseCapacity.setAddTime(new Date().getTime());
					userPurchaseCapacity.setUserId(user.getUserId());
					userPurchaseCapacity.setCapacityId((Integer) capacityMap.get("capacityId"));
					userPurchaseCapacity.setPrice((double)0);
					userPurchaseCapacity.setState((short) 1); // 1正常 2到期
					userPurchaseCapacity.setNote("注册赠送");
					userPurchaseCapacityDAO.addUserPurchaseCapacity(userPurchaseCapacity);

					// 初始化用户总容量
					UserCapacity userCapacity = new UserCapacity();
					userCapacity.setTotalCapacity((Long) capacityMap.get("size")); // 总容量
					userCapacity.setUserdCapacity((long) 0);
					userCapacity.setUserId(user.getUserId());
					userCapacityDAO.addUserCapacity(userCapacity);

					use = userDAO.selectUserByP(phone);
					
					log.debug("注册成功");
				} else {
					DataResult data = this.userCheck(use);// 验证用户的状态
					if (data != null) {
						return data;
					}
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", use.getUserId());

				// 获取用户上一次登录的登录uuid
				String uuid = userLoginRecordDAO.getUserUUId(use.getUserId());

				// 判断缓存中是否存在用户token
				String token = userCacheService.getUserToken(use.getUserId() + "");
				if (StrKit.isBlank(token) || !uuid.equals(phoneUUID)) {
					// 生成新的token
					String newToken = CommonKit.getToken(System.currentTimeMillis() + "", phoneUUID,
							use.getUserId() + "");
					map.put("token", newToken);
					// 将token缓存到redis中
					boolean bool = userCacheService.setUserToken(use.getUserId() + "", newToken);
					if (!bool) {
						log.debug("将用户token缓存失败,token:" + newToken);
						result.setStatusCode(500);
						result.setStatusMsg("服务器繁忙");
						return result;
					}
				} else {
					map.put("token", token);
				}

				// 添加用户登录记录
				UserLoginRecord userLoginRecord = new UserLoginRecord();
				userLoginRecord.setUserId(use.getUserId());
				userLoginRecord.setLoginTime(new Date().getTime());
				userLoginRecord.setLoginIp(ip);
				userLoginRecord.setEquipment(equipment);
				userLoginRecord.setUuid(phoneUUID);
				userLoginRecordDAO.insertUserLoginRecord(userLoginRecord);
				
				// 用户登录 处理购买的容量是否到期
				new CapacityThread(use.getUserId(), userPurchaseCapacityDAO, userCapacityDAO).start();

				result.setDataList(map);

				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("登录成功");
				log.debug("登录成功");
				return result;
			}
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("登录发生异常 异常信息如下：");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 验证用状态
	 * 
	 * @param us
	 * @return
	 */
	public DataResult userCheck(User user) {
		DataResult result = null;
		if (user == null) {
			result = new DataResult();
			result.setStatusCode(500);
			result.setStatusMsg("用户不存在");
			log.debug("用户不存在");
			return result;
		}

		if (user.getState() == 2) {
			result = new DataResult();
			result.setStatusCode(500);
			result.setStatusMsg("账户被冻结");
			log.debug("账户被冻结");
			return result;
		}
		if (user.getState() == 3) {
			result = new DataResult();
			result.setStatusCode(500);
			result.setStatusMsg("账户已被封");
			log.debug("账户已被封");
			return result;
		}

		if (user.getState() != 1) {
			result = new DataResult();
			result.setStatusCode(500);
			result.setStatusMsg("账户不存在");
			log.debug("账户不存在");
			return result;
		}
		return result;
	}

	@Override
	public DataResult passwordBack(User user, String smsCode) throws IOException {

		DataResult result = new DataResult();

		Long phone = user.getPhone();
		String password = user.getPassword();

		if (user == null || phone == null || StrKit.isBlank(password) || smsCode == null) {
			result.setStatusCode(500);
			result.setStatusMsg("请求参数不合法");
			log.debug("请求参数不合法：phone:" + phone + "smsCode:" + smsCode);
			return result;
		}

		User u = userDAO.selectUserByP(phone);
		if (u != null) {
			if (u.getState() == 1) { // 1 正常 2冻结 3封号

				// 验证短信验证码
				int checkResult = SMSService.checkSMSCode(smsCode, RedisKeys.getPasswordBack(user.getPhone()));
				if (checkResult == 3) {
					// 验证码超时
					result.setStatusCode(500);
					result.setStatusMsg("验证码超时");
					log.debug("验证码超时");
					return result;
				}
				if (checkResult == 2) {
					// 验证码错误
					result.setStatusCode(500);
					result.setStatusMsg("验证码错误");
					log.debug("验证码错误");
					return result;
				}
				if (checkResult == 1) {

					log.debug("开始对密码进行加密更新数据库");
					try {
						user.setPassword(PasswordHashKit.createHash(password));
						userDAO.passwordBack(user);

						result.setStatusCode(DataResult.OK);
						result.setStatusMsg("密码找回成功");
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						log.error("密码加密时发生异常" + e.getMessage());
					} catch (InvalidKeySpecException e) {
						e.printStackTrace();
						log.error("密码加密时发生异常" + e.getMessage());
					}
				}
			} else if (u.getState() == 2) {
				result.setStatusCode(500);
				result.setStatusMsg("账户被冻结");
				log.debug("账户被冻结");
			} else if (u.getState() == 3) {
				result.setStatusCode(500);
				result.setStatusMsg("账户已被封");
				log.debug("账户已被封");
			}

		} else {
			result.setStatusCode(500);
			result.setStatusMsg("用户不存在");
			log.debug("用户不存在");
		}

		return result;
	}

	@Override
	public DataResult exit(User user) throws IOException {

		log.debug("用户退出系统");

		DataResult result = new DataResult();
		coredis.delete(RedisKeys.redisUserToken(user.getUserId() + ""));

		userLoginRecordDAO.updateQuitTime(user.getUserId(), new Date().getTime()); // 记录用户的退出时间

		result.setStatusCode(DataResult.OK);
		result.setStatusMsg("退出系统成功");
		log.debug("销毁用户token认证成功！");
		return result;
	}

}
