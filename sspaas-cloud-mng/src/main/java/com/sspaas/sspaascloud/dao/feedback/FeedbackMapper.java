package com.sspaas.sspaascloud.dao.feedback;

import java.util.List;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface FeedbackMapper {
	// 查询所有反馈问题
	List<?> findAllFeedBack(PageCrt pageCrt);

	// 查找所有反馈数量
	Integer findAllFeedBackCount(PageCrt pageCrt);
	
	// 处理反馈
	Integer handleFeedback(Integer feedbackId);
}