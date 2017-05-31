package com.sspaas.sspaascloud.dao.appmng.capacity;

import java.util.List;
import java.util.Map;

public interface CapacityMapper {
	
	/**
	 * @Name: selectIdByType
	 * @Description: 查询类型为赠送的主键id
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月9日
	 * @return Map
	 * 				：capacityId		主键id
	 * 				：size			初始容量大小
	 */
	Map<String, Object> selectIdByType();
	
	
	/**
	 * @Name: capacityList
	 * @Description: 容量列表
	 * @Author: 郭海英（作者）
	 */
	List<Map<String, Object>> capacityList();
	
}