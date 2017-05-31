package com.sspaas.sspaascloud.dao.appmng.feedback;

import java.util.List;

import com.sspaas.sspaascloud.entity.appmng.FeedbackType;

public interface FeedbackTypeMapper {
	
	/**
	 * @Name: 获取反馈类型列表
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	List<FeedbackType> getFeedBackTypeList();
}