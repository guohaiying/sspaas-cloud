package com.sspaas.sspaascloud.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.user.UserMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.user.IUserService;

@Service
public class UserService implements IUserService {

	Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserMapper userDAO;
	
	@Override
	public PageCrt appUserList(PageCrt page) {
		logger.info("分页查询所有app用户");
        int rows = page.getRows();
        int pag = page.getPage();
        Integer records=userDAO.total(page);
        page.setRecords(records);
        page.setTotal(records % rows == 0 ? records / rows : records / rows + 1);
        int index = ((pag - 1) * rows);
        page.setIndex(index);
        List<Map<String, Object>> list = userDAO.selectAppUserList(page);
        page.setList(list);
        return page;
	}

	@Override
	public Integer editUser(int userId, byte state) {
		return userDAO.editUser(userId, state);
	}

	@Override
	public PageCrt loginRecord(PageCrt page) {
		logger.info("分页用户登录记录");
        int rows = page.getRows();
        int pag = page.getPage();
        Integer records=userDAO.loginRecordTotal(page);
        page.setRecords(records);
        page.setTotal(records % rows == 0 ? records / rows : records / rows + 1);
        int index = ((pag - 1) * rows);
        page.setIndex(index);
        List<Map<String, Object>> list = userDAO.selectLoginRecord(page);
        page.setList(list);
        return page;
	}
	
}
