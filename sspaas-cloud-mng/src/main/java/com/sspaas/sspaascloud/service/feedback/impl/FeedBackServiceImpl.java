package com.sspaas.sspaascloud.service.feedback.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.sspaas.sspaascloud.dao.feedback.FeedbackMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.feedback.IFeedBackService;

@Service
public class FeedBackServiceImpl implements IFeedBackService {

	Logger log = Logger.getLogger(FeedBackServiceImpl.class);

	@Resource
	private FeedbackMapper feedbackMapper;

	/** 查找所有反馈  */
	@Override
	public PageCrt findAllFeedBack(PageCrt pageCrt) {
		Integer records = feedbackMapper.findAllFeedBackCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list = feedbackMapper.findAllFeedBack(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}

	/** 处理反馈  */
	@Override
	public Integer handleFeedback(Integer feedbackId) {
		Integer result = feedbackMapper.handleFeedback(feedbackId);
		return result;
	}
	

}
