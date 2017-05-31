package com.sspaas.sspaascloud.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.permissions.PermissionsMapper;
import com.sspaas.sspaascloud.entity.Permissions;
import com.sspaas.sspaascloud.service.system.IPermissionsService;


@Service("permissionsService")
public class PermissionsServiceImpl implements IPermissionsService {
	Logger logger = Logger.getLogger(PermissionsServiceImpl.class);

	@Resource(name = "permissionsMapper")
	private PermissionsMapper permissionsMapper;

	@Override
	public void deletePermissions(List<String> menuList) {
		try {
			int result = permissionsMapper.deletePermissions(menuList);
			if (result < 1){
				logger.debug("删除权限出错！");
			}
		} catch (Exception e) {
			logger.debug("服务器繁忙！");
			e.printStackTrace();
		}
	}

	@Override
	public Integer deleteByRoleId(String roleId) {
		return permissionsMapper.deleteByRoleId(roleId);
	}

	@Override
	public Integer addMenuRole(Permissions permissions) {
		Integer result = permissionsMapper.insertPermissions(permissions);
		return result;
	}

	@Override
	public List<String> getMenuRoleByRoleId(String roleId) {
		List<String> list = permissionsMapper.getMenuRoleByRoleId(roleId);
		return list;
	}

	@Override
	public List<Permissions> getMenuRoleByRoleIdList(String roleId) {
		List<Permissions> list = permissionsMapper.getMenuRoleByRoleIdList(roleId);
		return list;
	}

}
