package com.sspaas.sspaascloud.service.user.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.user.IUserPurchaseCapacityService;

@Service
public class UserPurchaseCapacityService implements IUserPurchaseCapacityService {

	Logger logger = Logger.getLogger(UserPurchaseCapacityService.class);

	@Autowired
	private UserPurchaseCapacityMapper userPCapacityDAO;

	@Override
	public PageCrt getUserOrder(PageCrt pageCrt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
