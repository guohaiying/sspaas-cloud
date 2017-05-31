package com.sspaas.sspaascloud.dao.report;

import java.util.List;

import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportType;

public interface ReportTypeMapper {

	/**
	 * @Title: findAllReportType 
	 * @Description:获取举报类型
	 * @author: joker
	 * @Create:2016年12月23日下午4:35:38
	 * @param pageCrt
	 * @return PageCrt 
	 */
	List<?> findAllReportType(PageCrt pageCrt);

	/**
	 * @Title: findAllReportTypeCount 
	 * @Description:查询条目数量
	 * @author: joker
	 * @Create:2016年12月23日下午4:42:23
	 * @param pageCrt
	 * @return Integer 
	 */
	Integer findAllReportTypeCount(PageCrt pageCrt);

	/**
	 * @Title: deleteType 
	 * @Description:删除举报类型
	 * @author: joker
	 * @Create:2016年12月26日上午11:09:46
	 * @param reportTypeId
	 * @return Integer 
	 */
	Integer deleteType(Integer reportTypeId);

	/**
	 * @Title: addReportType 
	 * @Description:添加举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:06:58
	 * @param reportType
	 * @return Integer 
	 */
	Integer addReportType(ReportType reportType);

	/**
	 * @Title: updateReportType 
	 * @Description:更新举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:07:02
	 * @param reportType
	 * @return Integer 
	 */
	Integer updateReportType(ReportType reportType);

	/**
	 * @Title: getAllReportType 
	 * @Description:获得所有举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午3:26:13
	 * @return List<ReportType> 
	 */
	List<ReportType> getAllReportType();
}