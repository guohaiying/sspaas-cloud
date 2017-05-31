package com.sspaas.sspaascloud.service.appmng.feedback.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sspaas.sspaascloud.dao.appmng.feedback.FeedbackMapper;
import com.sspaas.sspaascloud.dao.appmng.feedback.FeedbackTypeMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.Feedback;
import com.sspaas.sspaascloud.entity.appmng.FeedbackType;
import com.sspaas.sspaascloud.kit.StrKit;
import com.sspaas.sspaascloud.service.appmng.feedback.IFeedBackService;

@Service
public class FeedBackService implements IFeedBackService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(FeedBackService.class);

	@Autowired
	private FeedbackMapper feedBackDAO;

	@Autowired
	private FeedbackTypeMapper feedBackgTypeDAO;

	/**
	 * @Name: 获取反馈类型列表
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult getFeedBackTypeList() {
		DataResult result = new DataResult();
		try {
			List<FeedbackType> list = feedBackgTypeDAO.getFeedBackTypeList();
			result.setDataList(list);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询反馈类型列表发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 提交反馈信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult saveFeedBack(Feedback feedback) {
		DataResult result = new DataResult();
		try {
			if (feedback == null || feedback.getUserId() == null || StrKit.isBlank(feedback.getTitle())) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("请求参数不合法  userId:"+feedback.getUserId()+"  title:"+feedback.getTitle());
				return result;
			}
			
			long addTime = new Date().getTime();
			feedback.setAddTime(addTime);
			feedback.setState((short) 2);// 1已处理 2未处理
			feedback.setFState((short) 1);// 1正常 2删除
			Integer rul = feedBackDAO.saveFeedBack(feedback);
			if (rul > 0) {
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("提交成功");
			} else {
				result.setStatusCode(500);
				result.setStatusMsg("提交失败,请稍后重试");
			}

		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询反馈类型列表发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 我的反馈列表
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult getMyFeedBackList(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {
			
			if (map == null || map.get("userId") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法!");
				log.debug("用户请求反馈列表    请求参数不合法 userId:" + map.get("userId"));
				return result;
			}
			List<Map<String, Object>> list = feedBackDAO.getMyFeedBackList(map);
			result.setDataList(list);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询反馈类型列表发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * @Name: 删除 清空反馈信息
	 * @Author: 郭海英（作者）
	 * @Return: DataResult
	 */
	@Override
	public DataResult delMyFeedBack(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {
			
			if (map == null || map.get("userId") == null || map.get("type")==null || map.get("feedbackId")==null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法!");
				log.debug("用户请求删除反馈    请求参数不合法 userId:" + map.get("userId")+" type:"+map.get("type")+" feedbackId:"+ map.get("feedbackId"));
				return result;
			}
			
			String type = map.get("type") + ""; // type=1 删除 type=2 清空
			Integer rul = 0;
			if (type.equals("1")) {
				rul = feedBackDAO.delMyFeedBack(map);
			} else {
				rul = feedBackDAO.emptyMyFeedBack(map);
			}
			if (rul >= 0) {
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("删除成功");
			}else{
				result.setStatusCode(DataResult.OK);
				result.setStatusMsg("删除失败");
			}
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询反馈类型列表发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

}
