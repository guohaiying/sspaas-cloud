package com.sspaas.sspaascloud.controller.appmng.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Report;
import com.sspaas.sspaascloud.service.appmng.report.IReportService;

/**
 * @Name:ReportController
 * @Description:文件举报操作相关:举报，举报列表
 * @author: dongshaowei
 * @Create:2016年12月13日下午16:45:08
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private IReportService reportService;
	
	/**
	 * @Name: login
	 * @Description: 举报
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param report
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public ResponseEntity<DataResult> report(@RequestBody Report report, HttpServletRequest request,
			HttpServletResponse response) {
		
		DataResult result = reportService.report(report);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: reportType
	 * @Description: 分页请求举报列表
	 * @Author: 董绍威（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016年12月13日
	 * @param map
	 * @param request
	 * @param response
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/reportType", method = RequestMethod.GET)
	public ResponseEntity<DataResult> reportType(Integer page, Integer pageCount, HttpServletRequest request,
			HttpServletResponse response) {
		
		DataResult result = reportService.reportType(page,pageCount);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}
