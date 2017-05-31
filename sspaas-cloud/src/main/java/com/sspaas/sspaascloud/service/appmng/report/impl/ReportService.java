package com.sspaas.sspaascloud.service.appmng.report.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.appmng.report.ReportMapper;
import com.sspaas.sspaascloud.dao.appmng.report.ReportTypeMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Report;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.service.appmng.report.IReportService;

@Service
public class ReportService implements IReportService {
	
	/** 日志记录器 */
	Logger log = Logger.getLogger(ReportService.class);

	@Autowired
	ReportMapper reportDAO;
	
	@Autowired
	ReportTypeMapper reportTypeDAO;
	
	@Override
	public DataResult report(Report report) {
		
		DataResult result = new DataResult();
		
		Integer userId = report.getUserId();
		Integer fileId = report.getFileId();
		String reportTypeId = report.getReportTypeId();
		
		if (report == null || userId == null && fileId == null && StrKit.isBlank(reportTypeId)) {
			result.setStatusCode(500);
			result.setStatusMsg("请求参数不合法!");
			log.debug("用户举报文件    请求参数不合法  report:" + report + "  userId:" + userId + "fileId:" +
			fileId + "reportTypeId:" + reportTypeId);
			return result;
		}
		
		report.setAddTime(new Date().getTime());
		report.setState(2); //1已处理 2未处理
		int res = reportDAO.addReport(report);
		if (res != 0) {
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("举报成功");
			log.debug("举报成功");
		} else {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("举报失败");
		}
		
		return result;
	}

	@Override
	public DataResult reportType(Integer page, Integer pageCount) {
		
		DataResult result = new DataResult();
		
		if (page == null && pageCount == null) {
			result.setStatusCode(500);
			result.setStatusMsg("请求参数不合法!");
			log.debug("用户获取举报列表   请求参数不合法  page:" + page + "pageCount:" +
					pageCount);
			return result;
		}
		
		List<Map<String, Object>> reportTypeList = reportTypeDAO.getReportType(page, pageCount);
		
		if (reportTypeList != null){
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取举报列表成功");
			result.setDataList(reportTypeList);
			log.debug("获取举报列表成功");
		} else {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("获取举报列表失败");
		}
		
		return result;
	}
	
}
