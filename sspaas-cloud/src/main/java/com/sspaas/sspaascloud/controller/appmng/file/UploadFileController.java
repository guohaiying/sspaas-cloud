package com.sspaas.sspaascloud.controller.appmng.file;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.storage.object.SwiftObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.controller.appmng.user.UserController;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IUploadFileService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

/**
 * @Name:UploadFileController
 * @Description:上传文件
 * @author: joker
 * @Create:2016年12月5日上午10:14:43
 */
@Controller
@RequestMapping("/file")
public class UploadFileController {

	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private SwiftUploadPojo swiftUploadPojo;

	/**
	 * @param userId
	 *            用户id
	 * @param fileId
	 *            文件上传文件夹id
	 * @param myfiles
	 *            文件
	 * @param request
	 * @return ResponseEntity<DataResult>
	 * @throws Exception
	 * @Name:uploadFile
	 * @Description:上传文件(可以上传多个文件)
	 * @author: joker
	 * @Create:2016年12月5日上午10:44:39
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<DataResult> uploadFile(Integer userId, Integer fileId, @RequestParam MultipartFile[] myfiles,
			HttpServletRequest request) throws Exception {
		/** 日志记录器 */
		Logger log = Logger.getLogger(UserController.class);
		log.debug("上传文件uploadFile-->userId:"+userId+",fileId:"+fileId);
		
		DataResult dataResult = new DataResult();

		// 遍历文件
		try {
			if (myfiles.length != 0) {
				for (MultipartFile myfile : myfiles) {
					if (myfile.isEmpty()) {

						dataResult.setStatusCode(500);
						dataResult.setStatusMsg("文件上传失败");
						return ResponseEntity.status(HttpStatus.OK).body(dataResult);
					}
					long size = myfile.getSize();
					// 判断用户容量是否够用
					Boolean flag = uploadFileService.findUserCapacity(userId, size);
					// 判断用户容量是否够用
					// 上传文件
					if (!flag) {
						dataResult.setStatusCode(401);
						dataResult.setStatusMsg("容量不够,请增加容量");
						return ResponseEntity.status(HttpStatus.OK).body(dataResult);
					}
					// 设置保存容器
					swiftUploadPojo.setContainerName(userId.toString());
					// 文件原始名称
					String originName = myfile.getOriginalFilename();
					// 判断是否有扩展名
					String fileType = "";
					if (originName.lastIndexOf(".") != -1) {
						fileType = originName.substring(originName.lastIndexOf("."));
					}
					// 保存文件到swift上
					swiftUploadPojo.setMultipartFile(myfile);
					String uploadName = SwiftUploadUtils.createObject(swiftUploadPojo, userId) + fileType;

					// 获取文件信息
					File file = new File();
					file.setUploadName(uploadName);
					// 获取文件上传时间
					OSClientV3 osV3 = SwiftUploadUtils.unscopedAuthentication(swiftUploadPojo);
					// 获取swift中保存的对象
					SwiftObject swiftObject = osV3.objectStorage().objects().get(swiftUploadPojo.getContainerName(),
							uploadName);
					if (swiftObject != null) {
						Date date = swiftObject.getLastModified();
						file.setAddTime(date.getTime());
					} else {
						dataResult.setStatusCode(500);
						dataResult.setStatusMsg("文件上传失败");
						return ResponseEntity.status(HttpStatus.OK).body(dataResult);
					}
					file.setOldName(originName);
					file.setSize(myfile.getSize());
					// 文件访问地址
					String filePath = swiftUploadPojo.getSwiftUrl() + osV3.getToken().getProject().getId() + "/"
							+ swiftUploadPojo.getContainerName() + "/" + uploadName;
					file.setFilePath(filePath);
					dataResult = uploadFileService.uploadFile(userId, file, fileId,myfile.getContentType());
				}

			} else {
				dataResult.setStatusCode(500);
				dataResult.setStatusMsg("文件上传失败");
				return ResponseEntity.status(HttpStatus.OK).body(dataResult);
			}
		} catch (Exception e) {
			log.error(e);
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("文件上传失败");
		}
		return ResponseEntity.status(HttpStatus.OK).body(dataResult);
	}
}
