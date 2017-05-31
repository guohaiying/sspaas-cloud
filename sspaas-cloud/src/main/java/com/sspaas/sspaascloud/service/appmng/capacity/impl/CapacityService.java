package com.sspaas.sspaascloud.service.appmng.capacity.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.appmng.capacity.CapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.service.appmng.capacity.ICapacityService;

@Service
public class CapacityService implements ICapacityService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(CapacityService.class);

	@Autowired
	private CapacityMapper capacityDAO;

	/**
	 * @Name: 容量列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@Override
	public DataResult capacityList() {
		DataResult result = new DataResult();
		try {
			List<Map<String, Object>> resultMap = capacityDAO.capacityList();
			result.setDataList(resultMap);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("获取容量列表发生异常     异常信息如下：");
			e.printStackTrace();
		}
		return result;
	}

	

}
