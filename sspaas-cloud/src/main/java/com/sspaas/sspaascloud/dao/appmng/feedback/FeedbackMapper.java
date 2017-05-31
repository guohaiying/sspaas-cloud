package com.sspaas.sspaascloud.dao.appmng.feedback;

import java.util.List;
import java.util.Map;
import com.sspaas.sspaascloud.entity.appmng.Feedback;

public interface FeedbackMapper {
	
	/**
	 * @Name: 提交反馈信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	int saveFeedBack(Feedback record);
	
	/**
	 * @Name: 我的反馈列表
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	List<Map<String,Object>> getMyFeedBackList(Map<String, Object> map);
	
	/**
	 * @Name: 删除我的反馈
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	Integer delMyFeedBack(Map<String, Object> map);
	
	/**
	 * @Name: 清空我的反馈
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	Integer emptyMyFeedBack(Map<String, Object> map);
	
}