package com.sspaas.sspaascloud.controller.appmng.file;

import java.util.HashMap;
import java.util.List;
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
import com.sspaas.sspaascloud.controller.appmng.user.UserController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IFileService;

/**
 * RecycleBinController
 * @Description:回收站操作相关:清空  删除  列表
 * @author: joker
 * @Create:2016年12月13日上午9:40:08
 */
@Controller
@RequestMapping("/recycleBin")
public class RecycleBinController {

	/** 日志记录器 */
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IFileService fileService;

	
	/**
	 * @Name: 回收站列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getRecycleBinFileList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getRecycleBinFileList(Integer userId, HttpServletRequest request) {
		DataResult result = new DataResult();

		log.debug("获取回收站列表  userId:" + userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("parentId", 0);
		map.put("state", 2);

		result = fileService.getFileList(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Title: deleteRecycleBin
	 * @Description:删除文件或文件夹
	 * @author: joker
	 * @Create:2016年12月14日下午4:58:44
	 * @param file fileId 文件id userId 用户Id 
	 * @return ResponseEntity<DataResult> 
	 */
	@RequestMapping(value = "/deleteRecycleBin", method = RequestMethod.DELETE)
	public ResponseEntity<DataResult> deleteRecycleBin(@RequestBody List<File> files) {
		DataResult dataResult = new DataResult();

		try {
			if (files != null) {
				for (File file : files) {
					fileService.deleteRecycleBin(file);
				}
			}
			dataResult.setStatusCode(200);
			dataResult.setStatusMsg("删除成功");
		} catch (Exception e) {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("删除失败");
			return ResponseEntity.status(HttpStatus.OK).body(dataResult);
		}

		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}
	
	
	/**
	 * @Title: regainFile
	 * @Description:恢复文件或文件夹
	 * @author: ghy
	 * @Create:2016年12月14日下午4:58:44
	 * @param file fileId 文件id userId 用户Id 
	 * @return ResponseEntity<DataResult> 
	 */
	@RequestMapping(value = "/regainFile", method = RequestMethod.POST)
	public ResponseEntity<DataResult> regainFile(@RequestBody List<File> files) {
		DataResult dataResult = new DataResult();

		try {
			if (files != null) {
				for (File file : files) {
					fileService.regainFile(file);
				}
			}
			dataResult.setStatusCode(200);
			dataResult.setStatusMsg("还原成功");
		} catch (Exception e) {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("还原失败");
			return ResponseEntity.status(HttpStatus.OK).body(dataResult);
		}

		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}

}
