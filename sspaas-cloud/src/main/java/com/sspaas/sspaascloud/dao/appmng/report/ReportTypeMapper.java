package com.sspaas.sspaascloud.dao.appmng.report;

import java.util.List;
import java.util.Map;

public interface ReportTypeMapper {
	
	/**
	 * @Name: getReportType
	 * @Description: 获取举报列表
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param page
	 * 				：当前页数
	 * @param pageCount
	 * 				：每页显示条数
	 * @return List<ReportType>
	 */
	List<Map<String, Object>> getReportType(int page, int pageCount);
}