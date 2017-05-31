package com.sspaas.sspaascloud.controller.report;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.controller.file.FileController;
import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportFile;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.report.IReportService;

/**
 * @Name:ReportController
 * @Description: 举报审查
 * @author: joker
 * @Create:2016年12月21日下午5:29:36
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	
	/**举报资源controller日志记录器*/
	Logger logger = Logger.getLogger(FileController.class);
	
	@Autowired
	private IReportService reportService;
	
	JSONKit json= new JSONKit();
	/**
	 * 跳转到举报资源管理页面
	 */
	@RequestMapping("/toReportList")
	public String toReportList(){
		
		return "/backstagemng/file-mng/report";
	}
	/**
	 * @Title: reportList 
	 * @Description:获取举报资源列表
	 * @author: joker
	 * @Create:2016年12月21日下午6:08:07
	 * @return String 
	 */
	@RequestMapping("/reportList")
	public void reportList(PageCrt pageCrt,String userName,String fileName,Integer fileTypeId,Integer reportTypeId,Integer userId,Integer fileId, Short state, HttpServletResponse response){
		pageCrt.getMap().put("userName", userName);
		pageCrt.getMap().put("fileName", fileName);
		pageCrt.getMap().put("userId", userId);
		pageCrt.getMap().put("fileId",fileId );
		pageCrt.getMap().put("state", state);
		pageCrt.getMap().put("reportTypeId", reportTypeId);
		pageCrt.getMap().put("fileTypeId", fileTypeId);
		PageCrt p = reportService.findAllReports(pageCrt);
		
		PrintWriter outputStream = super.getPrintWriter(response);
		this.objToJson(p,outputStream);
	}
	/**
	 * @Title: findAllFileType 
	 * @Description:获取所有被举报资源的文件类型
	 * @author: joker
	 * @Create:2016年12月26日下午11:06:27
	 * @return List<FileType> 
	 */
	@RequestMapping("/findAllFileType")
	public @ResponseBody List<FileType> findAllFileType(){
		
		return reportService.findAllFileType();
	}
	
	/**
	 * @Title: findReportFile 
	 * @Description:获取举报文件信息
	 * @author: joker
	 * @Create:2016年12月27日上午12:31:01
	 * @param fileId
	 * @return ReportFile 
	 */
	@RequestMapping("/findReportFile")
	public @ResponseBody ReportFile findReportFile(Integer fileId){
		
		try {
			ReportFile reportFile = reportService.findReportFile(fileId);
			return reportFile;
		} catch (Exception e) {
		
		
		}
		return null;
	}
	@RequestMapping("/auditReportFile")
	public String auditReportFile(Integer fileId,Integer userId,Integer reportId,Short fileState,Short userState, Short reportState){
		reportService.updateReportFile(fileId,fileState,userId,userState,reportId,reportState);
		
		return "/backstagemng/file-mng/report";
	}
}
