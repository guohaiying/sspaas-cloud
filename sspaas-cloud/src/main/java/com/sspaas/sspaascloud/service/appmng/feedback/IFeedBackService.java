package com.sspaas.sspaascloud.service.appmng.feedback;

import java.util.Map;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Feedback;

public interface IFeedBackService {
	
	/**
	 * @Name: 获取反馈类型列表
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	DataResult getFeedBackTypeList();
	
	/**
	 * @Name: 提交反馈信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	DataResult saveFeedBack(Feedback feedback);
	
	/**
	 * @Name: 查看我的反馈
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	DataResult getMyFeedBackList(Map<String, Object> map);
	
	/**
	 * @Name: 删除 清空反馈信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	DataResult delMyFeedBack(Map<String, Object> map);
	

	
}
