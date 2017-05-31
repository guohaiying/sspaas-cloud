package com.sspaas.sspaascloud.controller.feedback;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.kit.JSONKit;
import com.sspaas.sspaascloud.service.feedback.IFeedBackService;

@RequestMapping("/feedback")
@Controller
public class FeedBackController extends BaseController {
	
	/**用户管理controller日志记录器*/
	Logger logger = Logger.getLogger(FeedBackController.class);

	@Autowired
	private IFeedBackService feedbackService;

	JSONKit json= new JSONKit();
	
	/** 跳转到反馈管理页面 */
	@RequestMapping("/feedBackList")
	public String toPageFeedBack() {
		return "/backstagemng/feedback-mng/feedback";
	}
	
	
	/**查找所有反馈问题*/
	@RequestMapping("/selectAllList")
	public void findAllFeedBackType( PageCrt pageCrt,String username,String title,String content,Integer state,Integer fstate,Integer type, HttpServletResponse response) {
		pageCrt.getMap().put("username", username);
		pageCrt.getMap().put("title", title);
		pageCrt.getMap().put("content", content);
		pageCrt.getMap().put("state", state);
		pageCrt.getMap().put("fstate", fstate);
		pageCrt.getMap().put("type", type);
		PageCrt p = feedbackService.findAllFeedBack(pageCrt);
		PrintWriter out;
		out = super.getPrintWriter(response);
		this.objToJson(p, out);
	}
	
	/** 处理反馈 */
    @RequestMapping("/handleFeedback")
    public void handleFeedback(HttpServletResponse response,Integer feedbackId) {
        Integer result= feedbackService.handleFeedback(feedbackId);
        PrintWriter out = json.getPrintWriter(response);
        json.objToJson(result, out);
    }
    
}
