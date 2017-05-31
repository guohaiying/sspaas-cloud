package com.sspaas.sspaascloud.service.report;

import java.util.List;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportType;

public interface IReportTypeService {

	/**
	 * @Title: findAllReportType 
	 * @Description:查询所有举报类型
	 * @author: joker
	 * @Create:2016年12月26日上午11:05:11
	 * @param pageCrt
	 * @return PageCrt 
	 */
	public PageCrt findAllReportType(PageCrt pageCrt);

	/**
	 * @Title: deleteType 
	 * @Description:删除举报类型
	 * @author: joker
	 * @Create:2016年12月26日上午11:05:14
	 * @param reportTypeId
	 * @return Integer 
	 */
	public Integer deleteType(Integer reportTypeId);

	/**
	 * @Title: addReportType 
	 * @Description:添加举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:03:53
	 * @param reportType
	 * @return Integer 
	 */
	public Integer addReportType(ReportType reportType);

	/**
	 * @Title: updateReportType 
	 * @Description:更新举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:03:56
	 * @param reportType
	 * @return Integer 
	 */
	public Integer updateReportType(ReportType reportType);

	/**
	 * @Title: getAllReportType 
	 * @Description: 获得所有举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午3:25:21
	 * @return List<ReportType> 
	 */
	public List<ReportType> getAllReportType();


}
