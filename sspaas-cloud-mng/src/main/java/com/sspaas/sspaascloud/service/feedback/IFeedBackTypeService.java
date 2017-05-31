package com.sspaas.sspaascloud.service.feedback;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.FeedbackType;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface IFeedBackTypeService {

	/** 查找所有反馈类型 */
	PageCrt findAllFeedBackType(PageCrt pageCrt);
	
	/** 获取所有类型*/
	List<Map<String,Object>> getFeedbackType();
	
	/** 添加  */
	Integer addType(FeedbackType feedback);
	
	/** 修改  */
	Integer updateType(FeedbackType feedbackType);
	
	/** 删除   */
	Integer deleteType(Integer feedbackTypeId);

}
