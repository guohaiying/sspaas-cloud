package com.sspaas.sspaascloud.dao.feedback;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.FeedbackType;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface FeedbackTypeMapper {

	// 查询所有反馈类型
	List<?> findAllFeedBackType(PageCrt pageCrt);

	// 查找所有反馈类型数量
	Integer findAllFeedBackTypeCount(PageCrt pageCrt);
	
	//获取所有类型
	List<Map<String, Object>> getFeedbackType();
	
	//添加
	Integer addType(FeedbackType feedbackType);
	
	//修改
	Integer updateType(FeedbackType feedbackType);
	
	//删除
	Integer deleteType(Integer feedbackTypeId);

}