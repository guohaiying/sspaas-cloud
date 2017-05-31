package com.sspaas.sspaascloud.controller.feedback;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.FeedbackType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.feedback.IFeedBackTypeService;


@RequestMapping("/feedbackType")
@Controller
public class FeedBackTypeController extends BaseController {
	
	/**用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(FeedBackTypeController.class);

	@Autowired
	private IFeedBackTypeService feedbackTypeService;

	JSONKit json= new JSONKit();
	
	/** 跳转到反馈类型管理页面 */
	@RequestMapping("/feedBackTypeList")
	public String toPageFeedBackType() {
		return "/backstagemng/feedback-mng/feedbacktype";
	}
	
	/**查找所有反馈类型*/
	@RequestMapping("/selectAllList")
	public void findAllFeedBackType( PageCrt pageCrt, HttpServletResponse response) {
		PageCrt p = feedbackTypeService.findAllFeedBackType(pageCrt);
		PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	 /** 获取所有类型*/
    @RequestMapping("/getFeedbackType")
    public void getTypeRoot(HttpServletResponse response) {
        List<Map<String,Object>> listmap= feedbackTypeService.getFeedbackType();
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(listmap, out);
    }
	
	 /** 添加 */
    @RequestMapping("/addType")
    public void addType(HttpServletResponse response, PageCrt pageCrt, FeedbackType feedbackType) {
        Integer result= feedbackTypeService.addType(feedbackType);
        PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(result, out);
    }
    
    /** 添加 */
    @RequestMapping("/updateType")
    public void updateType(HttpServletResponse response, PageCrt pageCrt, FeedbackType feedbackType) {
        Integer result= feedbackTypeService.updateType(feedbackType);
        PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(result, out);
    }
    
    /** 删除 */
    @RequestMapping("/deleteType")
    public void deleteType(HttpServletResponse response, Integer feedbackTypeId) {
        Integer result= feedbackTypeService.deleteType(feedbackTypeId);
        PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(result, out);
    }
    
    
}
