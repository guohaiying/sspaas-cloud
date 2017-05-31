package com.sspaas.sspaascloud.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.useribm.UserManagerMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.UserManager;
import com.sspaas.sspaascloud.kit.DateKit;
import com.sspaas.sspaascloud.service.user.IUserManagerService;

@Service
public class UserManagerServiceImpl implements IUserManagerService {

	Logger logger = Logger.getLogger(UserManagerServiceImpl.class);

	@Resource
	private UserManagerMapper userManagerMapper;

	public UserManager checkLogin(UserManager user) {
		 return  userManagerMapper.select(user);
	
	}

	//登录时更新时间
	public void updateUser(UserManager userManager) {
		logger.debug("上次登录时间为"+userManager.getLoginTime());
		userManager.setLastLoginTime(userManager.getLoginTime());
		userManager.setLoginTime(DateKit.getStringTime("yyyy-MM-dd HH:mm:ss"));
		logger.debug("总数为"+userManager.getCount()+1);
		userManager.setCount(userManager.getCount()+1);
		try {
			logger.debug("登录时开始更新时间和总登陆数");
			this.userManagerMapper.updateUser(userManager);
		} catch (Exception e) {
			logger.error("程序出现错误错误信息为"+e.getMessage());
		}

	}

	//用户注册
	public void saveUserManager(UserManager user) {
		this.userManagerMapper.saveUserManager(user);

	}

	//检查用户名唯一性
	public boolean  checkName(UserManager user) {
		List<UserManager> list= userManagerMapper.checkName(user);
		System.out.println(list.size());
		if (list.size()==0) {
			return  true;
		}else{
			return false;
		}
	}

	// 查询总条数
	public int selectCount() {
		return 	userManagerMapper.selectTotalPage();
	}

	//分页查询
	public List<Map> selectPaging(PageCrt page) {
		return userManagerMapper.selectePaging(page);
	}

	//更新用户
	public void updateUserById(UserManager user) {
		userManagerMapper.updateByPrimaryKeySelective(user);

	}

	//删除用户
	public void delectUser(UserManager user) {
		userManagerMapper.deleteByPrimaryKey(user.getUserId());

	}

	public void updateUserPassword(UserManager user) {
		userManagerMapper.updateUserPassword(user);
	}

	public UserManager findUserById(UserManager user) {
		return userManagerMapper.findUserById(user.getUserId());
	}

}
