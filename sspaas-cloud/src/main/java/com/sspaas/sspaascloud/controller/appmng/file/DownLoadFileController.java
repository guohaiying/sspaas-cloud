package com.sspaas.sspaascloud.controller.appmng.file;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openstack4j.model.common.DLPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IDownloadFileService;
import com.sspaas.sspaascloud.service.appmng.file.IFileService;

/**
 * @Name:DownLoadFileController
 * @Description:文件下载
 * @author: joker
 * @Create:2016年12月5日下午7:39:53
 */
@Controller
@RequestMapping("/file")
public class DownLoadFileController {

	@Autowired
	private IDownloadFileService downloadFileService;

	@Autowired
	private IFileService fileService;

	Logger logger = Logger.getLogger(DownLoadFileController.class);

	/**
	 * @Title: downloadFile 
	 * @Description:
	 * @author: joker
	 * @Create:2016年12月6日下午3:50:36
	 * @param userId
	 * @param fileId
	 * @return 成功返回: ResponseEntity<byte[]> 异常返回:ResponseEntity<DataResult>
	 */
	@RequestMapping(value="/downloadFile/{userId}/proxy/file/{fileId}", method= RequestMethod.GET)
	public ResponseEntity<?> downloadFile(@PathVariable("userId")Integer userId, @PathVariable("fileId")Integer fileId) {
		//获得文件输入流
		DLPayload dLPayload= downloadFileService.downloadFile(userId,fileId);
	    //获取下载文件信息
	    File file =fileService.getFile(fileId);
	    //设置下载信息
		try {
			String dfileName = new String(file.getOldName().getBytes("gb2312"), "iso8859-1");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(dLPayload.getInputStream(),file.getSize()), headers, HttpStatus.OK);
		} catch (IOException e) {
			logger.error("文件下载时发生异常:"+e.getMessage());
			DataResult result = new DataResult();
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			return  ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}
	
	
	
	/**
	 * @Title: downloadFile
	 * @Description:文件下载
	 * @author: joker
	 * @Create:2016年12月20日下午3:18:02
	 * @param userId
	 * @param fileId
	 * @param response
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/downloadFile/{userId}/file/{fileId}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadFile(@PathVariable("userId") Integer userId, @PathVariable("fileId") Integer fileId,
			HttpServletResponse response) {
		DataResult result = new DataResult();
		if (userId != null && fileId != null) {
			try {
				// 获取文件下载地址
				String path = downloadFileService.getTempUrl(userId, fileId);
				if (path==null) {
					result.setStatusCode(500);
					result.setStatusMsg("服务器繁忙 ");
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
				//重定向到swift下载文件
				response.sendRedirect(path);
			} catch (Exception e) {
				
				result.setStatusCode(500);
				result.setStatusMsg("服务器繁忙");
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		}

		return null;
	}
}
