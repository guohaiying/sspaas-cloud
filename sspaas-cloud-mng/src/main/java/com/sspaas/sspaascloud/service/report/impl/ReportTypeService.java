package com.sspaas.sspaascloud.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.report.ReportTypeMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportType;
import com.sspaas.sspaascloud.service.report.IReportTypeService;

@Service
public class ReportTypeService implements IReportTypeService {
	@Autowired
	private ReportTypeMapper reportTpyeMapper;
	@Override
	public PageCrt findAllReportType(PageCrt pageCrt) {
		Integer records =reportTpyeMapper.findAllReportTypeCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list= reportTpyeMapper.findAllReportType(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}
	@Override
	public Integer deleteType(Integer reportTypeId) {
		
		return reportTpyeMapper.deleteType(reportTypeId);
	}
	@Override
	public Integer addReportType(ReportType reportType) {

		return reportTpyeMapper.addReportType(reportType);
	}
	@Override
	public Integer updateReportType(ReportType reportType) {

		return reportTpyeMapper.updateReportType(reportType);
	}
	@Override
	public List<ReportType> getAllReportType() {
		return reportTpyeMapper.getAllReportType();
	}


}
