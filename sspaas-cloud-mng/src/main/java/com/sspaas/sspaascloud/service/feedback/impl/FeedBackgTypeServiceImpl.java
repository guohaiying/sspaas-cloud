package com.sspaas.sspaascloud.service.feedback.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.feedback.FeedbackTypeMapper;
import com.sspaas.sspaascloud.entity.FeedbackType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.feedback.IFeedBackTypeService;

@Service
public class FeedBackgTypeServiceImpl implements IFeedBackTypeService {

	Logger log = Logger.getLogger(FeedBackgTypeServiceImpl.class);

	@Resource
	private FeedbackTypeMapper feedbacktypeMapper;

	
	/** 查找所有反馈类型*/
	@Override
	public PageCrt findAllFeedBackType(PageCrt pageCrt) {
		Integer records = feedbacktypeMapper.findAllFeedBackTypeCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list = feedbacktypeMapper.findAllFeedBackType(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}
	
	/** 获取所有类型*/
	@Override
	public List<Map<String, Object>> getFeedbackType() {
		List<Map<String, Object>> list = feedbacktypeMapper.getFeedbackType();
		return list;
	}


	/** 添加*/
	@Override
	public Integer addType(FeedbackType feedbackType) {
		feedbackType.setAddTime(new Date().getTime());
		Integer result = feedbacktypeMapper.addType(feedbackType);
		return result;
	}

	/** 修改   */
	@Override
	public Integer updateType(FeedbackType feedbackType) {
		Integer result = feedbacktypeMapper.updateType(feedbackType);
		return result;
	}

	/** 删除   */
	@Override
	public Integer deleteType(Integer feedbackTypeId) {
		Integer result = feedbacktypeMapper.deleteType(feedbackTypeId);
		return result;
	}



}
