package com.sspaas.sspaascloud.controller.appmng.user;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.service.appmng.capacity.ICapacityService;
import com.sspaas.sspaascloud.service.appmng.user.IUserPurchaseCapacityService;

@Controller
@RequestMapping("/userCapacity")
public class UserCapacityController extends BaseController {
	/** 日志记录器 */
	Logger log = Logger.getLogger(UserCapacityController.class);

	@Autowired
	private IUserPurchaseCapacityService userCapacityService;
	
	@Autowired
	private ICapacityService capacityService;
	

	/**
	 * @Name: 个人扩容记录
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getUserCapacityList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getUserCapacityList(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		DataResult result = new DataResult();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);

		log.debug("获取个人扩容记录   参数说明：userId:" + map.get("userId"));

		result = userCapacityService.getUserCapacityList(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	/**
	 * @Name: 购买容量
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/buyCapacity", method = RequestMethod.POST)
	public ResponseEntity<DataResult> buyCapacity(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		DataResult result = new DataResult();

		result = userCapacityService.buyCapacity(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	/**
	 * @Name: 容量列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/capacityList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> capacityList(HttpServletRequest request) {
		DataResult result = new DataResult();

		result = capacityService.capacityList();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	

}
