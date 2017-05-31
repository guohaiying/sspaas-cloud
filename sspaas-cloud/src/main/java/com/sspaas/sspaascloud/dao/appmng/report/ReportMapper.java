package com.sspaas.sspaascloud.dao.appmng.report;

import com.sspaas.sspaascloud.entity.appmng.Report;

public interface ReportMapper {
	
	/**
	 * @Name: addReport
	 * @Description: 举报
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param report
	 * @return Integer
	 */
	Integer addReport(Report report);
}