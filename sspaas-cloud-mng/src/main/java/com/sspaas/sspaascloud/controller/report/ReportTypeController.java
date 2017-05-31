package com.sspaas.sspaascloud.controller.report;


import java.io.PrintWriter;
import java.util.Date;
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
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportType;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.report.IReportService;
import com.sspaas.sspaascloud.service.report.IReportTypeService;


/**
 * @Name:ReportTypeController
 * @Description:举报类型
 * @author: joker
 * @Create:2016年12月23日下午4:20:49
 */
@Controller
@RequestMapping("/reportType")
public class ReportTypeController extends BaseController {
	
	/**举报类型资源controller日志记录器*/
	Logger logger = Logger.getLogger(FileController.class);
	@Autowired
	private IReportTypeService reportTypeService;
	JSONKit json= new JSONKit();
	/**
	 * 跳转到举报资源类型管理页面
	 */
	@RequestMapping("/toReportTypeList")
	public String toReportTypeList(){
		
		return "/backstagemng/file-mng/reporttype";
	}
	
	/**
	 * @Title: reportTypeList 
	 * @Description:举报类型列表
	 * @author: joker
	 * @Create:2016年12月23日下午5:10:31
	 * @param pageCrt
	 * @param state
	 * @param response void 
	 */
	@RequestMapping("/reportTypeList")
	public void reportTypeList(PageCrt pageCrt, HttpServletResponse response){
		PageCrt p = reportTypeService.findAllReportType(pageCrt);
		
		PrintWriter outputStream = super.getPrintWriter(response);
		this.objToJson(p,outputStream);
	}
	/**
	 * @Title: reportTypeList 
	 * @Description:获取所有reportType
	 * @author: joker
	 * @Create:2016年12月26日下午3:22:31
	 * @param pageCrt
	 * @param response void 
	 */
	@RequestMapping("/getAllReportType")
	public @ResponseBody List<ReportType> getAllReportType( HttpServletResponse response){
		List<ReportType> list = reportTypeService.getAllReportType();
		
		return list ;
				 
	}
	/**
	 * @Title: addReportType 
	 * @Description:添加举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:14:25
	 * @param reportType
	 * @param response void 
	 */
	@RequestMapping("/addReportType")
	public String addReportType(ReportType reportType, HttpServletResponse response){
		reportType.setAddTime(new Date().getTime());
		reportTypeService.addReportType(reportType);
		return "/backstagemng/file-mng/reporttype";
	}
	/**
	 * @Title: updateReportType 
	 * @Description:更新举报类型
	 * @author: joker
	 * @Create:2016年12月26日下午2:14:29
	 * @param reportType
	 * @param response void 
	 */
	@RequestMapping("/updateReportType")
	public String updateReportType(ReportType reportType, HttpServletResponse response){
		reportType.setAddTime(new Date().getTime());
		 reportTypeService.updateReportType(reportType);
		 return "/backstagemng/file-mng/reporttype";	

	}
	
	/**
	 * @Title: deleteReportType 
	 * @Description: 删除举报类型
	 * @author: joker
	 * @Create:2016年12月26日上午11:07:32
	 * @param reportTypeId
	 * @param response void 
	 */
	@RequestMapping("/deleteReportType")
	public void deleteReportType(Integer reportTypeId, HttpServletResponse response){
		 Integer result= reportTypeService.deleteType(reportTypeId);
	        PrintWriter out = json.getPrintWriter(response);
	        json.objToJson(result, out);	

	
	}
	
	
	
}
