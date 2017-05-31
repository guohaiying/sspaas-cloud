package com.sspaas.sspaascloud.controller.appmng.feedback;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Feedback;
import com.sspaas.sspaascloud.service.appmng.feedback.IFeedBackService;

@Controller
@RequestMapping("/feedBack")
@SuppressWarnings("unchecked")
public class FeedBackController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(FeedBackController.class);

	@Autowired
	private IFeedBackService feedbackService;

	/**
	 * @Name: 反馈类型列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getFeedBackTypeList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getFeedBackTypeList(HttpServletRequest request) {
		DataResult result = new DataResult();

		log.debug("获取反馈类型列表   参数说明：");

		result = feedbackService.getFeedBackTypeList();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Name: 提交反馈记录
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/saveFeedBack", method = RequestMethod.POST)
	public ResponseEntity<DataResult> saveFeedBack(@RequestBody Feedback feedback, HttpServletRequest request) {
		DataResult result = new DataResult();
		
		log.debug("提交反馈记录   参数说明：userId:"+feedback.getUserId()+"     content:"+feedback.getContent()+"     type:"+feedback.getType());

		result = feedbackService.saveFeedBack(feedback);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: 查看我的反馈
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getMyFeedBackList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getMyFeedBackList(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		DataResult result = new DataResult();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		
		log.debug("提交反馈记录   参数说明：userId:"+map.get("userId"));

		result = feedbackService.getMyFeedBackList(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * @Name: 删除 清空 反馈
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/delMyFeedBack", method = RequestMethod.DELETE)
	public ResponseEntity<DataResult> delMyFeedBack(@RequestBody Object o, HttpServletRequest request) {
		DataResult result = new DataResult();
		
		Map<String, Object> map = (Map<String, Object>) o;
		
		log.debug("删除反馈记录   参数说明：userId:"+map.get("userId")+"  type:"+map.get("type"));

		result = feedbackService.delMyFeedBack(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
