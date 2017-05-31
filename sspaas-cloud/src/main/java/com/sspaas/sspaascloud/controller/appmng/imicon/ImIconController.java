package com.sspaas.sspaascloud.controller.appmng.imicon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;
@Controller
@RequestMapping("/imicom")
public class ImIconController {
	
	
	@Autowired
	private SwiftUploadPojo uploadPojo; 
	
	@RequestMapping(value = "/uploadImIcom", method = RequestMethod.POST)
	public ResponseEntity<DataResult> upUserIcon(Integer userId,@RequestParam MultipartFile file) {
		DataResult result = new DataResult();

		uploadPojo.setMultipartFile(file);
		uploadPojo.setContainerName("imicon");
		String url = SwiftUploadUtils.createObject(uploadPojo);
		Map<String,String> data = new HashMap<>();
		data.put("url", url);
		result.setStatusCode(DataResult.OK);
		result.setStatusMsg("上传图片成功");
		result.setDataList(data);
	
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}
