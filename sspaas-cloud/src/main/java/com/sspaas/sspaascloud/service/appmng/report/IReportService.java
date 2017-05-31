package com.sspaas.sspaascloud.service.appmng.report;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Report;

public interface IReportService {
	
	/**
	 * @Name: report
	 * @Description: 举报
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param report
	 * @return DataResult
	 */
	public DataResult report(Report report);
	
	/**
	 * @Name: reportType
	 * @Description: 分页请求举报列表
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param page
	 * @param pageCount
	 * @return DataResult
	 */
	public DataResult reportType(Integer page, Integer pageCount);
	
}
