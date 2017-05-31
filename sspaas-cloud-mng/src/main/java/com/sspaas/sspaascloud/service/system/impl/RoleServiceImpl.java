package com.sspaas.sspaascloud.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.role.RoleMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.Role;
import com.sspaas.sspaascloud.service.system.IRoleService;



@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;

	// 查询全部角色
	@Override
	public PageCrt findRole(PageCrt page) {
		logger.info("分页查询所有角色");
		Integer records=roleMapper.total(page);
		page.setRecords(records);
		List<Role> list = roleMapper.selectRoleByPage(page);
		page.setList(list);
		return page;
	}

	@Override
	public Map selectRoleByUser(String userId) {
		return roleMapper.selectRoleByUser(userId);
	}

	@Override
	public List<Map> selectRole() {
		return roleMapper.selectRole();
	}

	// 添加角色
	@Override
	public Integer addRole(Role role) {
		Integer result = roleMapper.insertRole(role);
		return result;
	}

	@Override
	public Integer updateRoleById(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public Integer deleteRoleById(Role role) {
		return roleMapper.deleteByPrimaryKey(role.getRoleId());
	}

}
