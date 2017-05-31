package com.sspaas.sspaascloud.controller.appmng.android;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sspaas.sspaascloud.controller.appmng.feedback.FeedBackController;
import com.sspaas.sspaascloud.controller.commons.BaseController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.service.appmng.android.IAndroidVersionService;

@Controller
@RequestMapping("/android")
public class AndroidVersionController extends BaseController{
	/** 日志记录器 */
	Logger log = Logger.getLogger(FeedBackController.class);
	@Autowired
	IAndroidVersionService androidVersionService;

	@RequestMapping(value = "/lastversion", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getLastVersion(HttpServletRequest request) {
		DataResult result = new DataResult();

		log.debug("获取安卓最新版本信息");

		result = androidVersionService.getLastVersion();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
