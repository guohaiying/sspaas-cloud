package com.sspaas.sspaascloud.service.appmng.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.service.appmng.user.IUserPurchaseCapacityService;

@Service
public class UserPurchaseCapacityService implements IUserPurchaseCapacityService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(UserPurchaseCapacityService.class);

	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;

	/**
	 * @Name: 个人扩容记录
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@Override
	public DataResult getUserCapacityList(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {
			if (map == null || map.get("userId") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户请求个人扩容记录参数为空  userId:" + map.get("userId"));
				return result;
			}
			List<Map<String, Object>> resultMap = userPurchaseCapacityDAO.getUserCapacityList(map);
			result.setDataList(resultMap);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("获取个人扩容记录发生异常     异常信息如下：");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Name: 购买容量
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@Override
	public DataResult buyCapacity(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {
			// if (map == null || map.get("userId") == null) {
			// result.setStatusCode(500);
			// result.setStatusMsg("请求参数不合法");
			// log.debug("用户请求个人扩容记录参数为空 userId:" + map.get("userId"));
			// return result;
			// }
			// 调用支付

			// 支付成功记录
			map.put("addTime", new Date().getTime());
			map.put("payTime", new Date().getTime());
//			map.put("matureTime", new Date().getTime());
//			map.put("price", );
			map.put("state", 1);
			map.put("payState", 1);
			//map.put("channel", 1);
			Integer res = userPurchaseCapacityDAO.buyCapacity(map);
			if (res <= 0) {
				result.setStatusCode(500);
				result.setStatusMsg("失败");
				return result;
			}
			// 用户常量值增加

			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("支付成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("购买容量发生异常     异常信息如下：");
			e.printStackTrace();
		}
		return result;
	}

}
