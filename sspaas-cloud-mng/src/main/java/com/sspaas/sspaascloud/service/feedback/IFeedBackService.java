package com.sspaas.sspaascloud.service.feedback;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface IFeedBackService {

	/** 查找所有反馈*/
	PageCrt findAllFeedBack(PageCrt pageCrt);
	
	/** 处理反馈 */
	Integer handleFeedback(Integer feedbackId);
	


}
