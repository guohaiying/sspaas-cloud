package com.sspaas.sspaascloud.service.report.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.report.ReportMapper;
import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportFile;
import com.sspaas.sspaascloud.service.report.IReportService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

@Service
public class ReportService implements IReportService {
	
	/** 日志记录器 */
	Logger log = Logger.getLogger(ReportService.class);
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private SwiftUploadPojo swiftUploadPojo;
	@Override
	public PageCrt findAllReports(PageCrt pageCrt) {
		Integer records = reportMapper.findAllReportsCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list = reportMapper.findAllReports(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}
	@Override
	public List<FileType> findAllFileType() {
		
		return reportMapper.findAllFileType();
	}
	@Override
	public ReportFile findReportFile(Integer fileId) throws Exception {
		ReportFile reportFile = reportMapper.findReportFile(fileId);
		swiftUploadPojo.setContainerName(reportFile.getUserId().toString());
	
		String url = SwiftUploadUtils.getTempUrl(swiftUploadPojo, reportFile.getUploadName(), reportFile.getOldName());
		reportFile.setFilePath(url);

		
		return reportFile;
	}
	@Override
	public void updateReportFile(Integer fileId, Short fileState, Integer userId, Short userState, Integer reportId,
			Short reportState) {
		reportMapper.updateReportState(reportId,reportState);
		reportMapper.updateFileState(fileId,fileState);
		reportMapper.updateUserState(userId,userState);
		
	}
	
}
