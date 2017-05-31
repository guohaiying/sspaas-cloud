package com.sspaas.sspaascloud.controller.appmng.file;

import java.util.Date;
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

import com.sspaas.sspaascloud.common.FileCommon;
import com.sspaas.sspaascloud.controller.appmng.user.UserController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IFileService;

/**
 * @Name:FileController
 * @Description:文件操作相关:移动、删除、重命名、创建文件夹、查看、排序、搜索
 * @author: joker
 * @Create:2016年12月13日上午9:40:08
 */
@Controller
@RequestMapping("/file")
public class FileController {

	/** 日志记录器 */
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IFileService fileService;

	/**
	 * @Title: addFolder
	 * @Description:新建文件夹
	 * @author: joker
	 * @Create:2016年12月13日下午2:58:02
	 * @param file
	 *            需要参数:oldName:新建文件夹名称 userId:用户id 父文件夹id:parentId
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/addFolder", method = RequestMethod.POST)
	public ResponseEntity<DataResult> addFolder(@RequestBody File file) {
		DataResult dataResult = new DataResult();
		// 构建新建文件夹信息
		if (file.getOldName() != null && file.getUserId() != null) {
			// 查询可用文件名
			file.setType(FileCommon.FILE_TYPE_FOLDER);
			String fileName = fileService.searchFileName(file);
			file.setOldName(fileName);
			file.setFileTypeId(FileCommon.FILE_TYPE_ID);
			if (file.getParentId() == null) {
				file.setParentId(0);
			}
			file.setAddTime(new Date().getTime());
			// 设置文件状态
			if (file.getState() == null) {
				file.setState(FileCommon.FILE_STATE_NORMAL);
			}
			// 保存文件
			dataResult = fileService.saveFile(file);
		} else {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("文件夹创建失败");
		}

		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}


	/**
	 * @Name: 文件类型列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getFileTypeList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getFileTypeList(HttpServletRequest request) {
		DataResult result = new DataResult();

		result = fileService.getFileTypeList();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Name: 文件列表
	 * @Author: 郭海英（作者）
	 * @Return: ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/getFileList", method = RequestMethod.GET)
	public ResponseEntity<DataResult> getFileList(Integer userId, Integer parentId, String sort, String name,
			Integer fileTypeId, HttpServletRequest request) {
		DataResult result = new DataResult();

		log.debug("获取文件列表  userId:" + userId + " parentId:" + parentId + " sort:" + sort + " name:" + name
				+ " fileTypeId:" + fileTypeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("parentId", parentId);
		map.put("sort", sort);
		map.put("name", name);
		map.put("fileTypeId", fileTypeId);
		map.put("state", 1);

		result = fileService.getFileList(map);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @Title: deleteFile 
	 * @Description:删除文件或文件夹
	 * @author: joker
	 * @Create:2016年12月14日下午4:58:44
	 * @param file fileId 文件id userId 用户Id 
	 * @return ResponseEntity<DataResult> 
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
	public ResponseEntity<DataResult> deleteFile(@RequestBody List<File> files) {
		DataResult dataResult = new DataResult();

		try {
			if (files != null) {
				for (File file : files) {
					fileService.deleteFile(file);
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
	 * @Title: removeFile
	 * @Description: 移动文件或文件夹
	 * @author: joker
	 * @Create:2016年12月14日下午4:02:19
	 * @param file
	 *            fileId:文件id parentId: 要移动到的文件夹id userId: 用户id
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/removeFile", method = RequestMethod.POST)
	public ResponseEntity<DataResult> removeFile(@RequestBody List<File> files) {
		DataResult dataResult = new DataResult();
		if (files.size() > 0) {
			try {
				fileService.removeFile(files);
				dataResult.setStatusCode(DataResult.OK);
				dataResult.setStatusMsg("移动文件成功");
			} catch (Exception e) {
				dataResult.setStatusCode(500);
				dataResult.setStatusMsg("移动文件失败");
				return ResponseEntity.status(HttpStatus.OK).body(dataResult);
			}
		} else {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("移动文件失败");
		}
		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}

	/**
	 * @Title: renameFile
	 * @Description:
	 * @author: joker
	 * @Create:2016年12月14日下午3:39:12
	 * @param file
	 *            oldName:更改的名字 fileId: 更改的文件id type:文件类型 是文件夹(1)还是文件(2)
	 *            userId:用户Id
	 * @return ResponseEntity<DataResult>
	 */
	@RequestMapping(value = "/renameFile", method = RequestMethod.POST)
	public ResponseEntity<DataResult> renameFile(@RequestBody File file) {
		DataResult dataResult = new DataResult();
		// 查询可用文件名
		if (file.getFileId() != null && file.getType() != null && file.getOldName() != null) {
			try {
				String name = fileService.searchFileName(file);
				file.setOldName(name);
				file.setUpdateTime(new Date().getTime());
				// 更新文件
				fileService.updateFile(file);

				dataResult.setStatusCode(DataResult.OK);
				dataResult.setStatusMsg("重命名成功");
			} catch (Exception e) {
				dataResult.setStatusCode(500);
				dataResult.setStatusMsg("重命名失败");
				return ResponseEntity.status(HttpStatus.OK).body(dataResult);
			}
		} else {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("重命名失败");
		}

		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}

}
