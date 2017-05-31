package com.sspaas.sspaascloud.service.appmng.capacity;

import com.sspaas.sspaascloud.entity.appmng.DataResult;

public interface ICapacityService {
	
	
	/**
	 * @Name: 容量列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	DataResult capacityList();
	
}
