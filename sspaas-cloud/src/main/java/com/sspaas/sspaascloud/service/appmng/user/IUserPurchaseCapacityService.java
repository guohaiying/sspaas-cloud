package com.sspaas.sspaascloud.service.appmng.user;

import java.util.Map;

import com.sspaas.sspaascloud.entity.appmng.DataResult;

public interface IUserPurchaseCapacityService {
	
	
	/**
	 * @Name: 个人扩容记录
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult getUserCapacityList(Map<String,Object> map);
	
	/**
	 * @Name: 购买容量
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult buyCapacity(Map<String, Object> map);
	
}
