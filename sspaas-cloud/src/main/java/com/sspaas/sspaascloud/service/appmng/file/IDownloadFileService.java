package com.sspaas.sspaascloud.service.appmng.file;

import org.openstack4j.model.common.DLPayload;

/**
 * @Name:IDownloadFileService
 * @Description:下载文件
 * @author: joker
 * @Create:2016年12月6日上午10:26:16
 */
public interface IDownloadFileService {

	/**
	 * @Title: downloadFile 
	 * @Description:使用openstack4j 获取文件输出流
	 * @author: joker
	 * @Create:2016年12月20日下午3:24:52
	 * @param userId
	 * @param fileId
	 * @return DLPayload 
	 */
	DLPayload downloadFile(Integer userId, Integer fileId);

	/**
	 * @Title: getTempUrl 
	 * @Description:获取下载文件临时url
	 * @author: joker
	 * @Create:2016年12月20日下午3:16:38
	 * @param userId 用户id
	 * @param fileId 文件id
	 * @return String 
	 */
	String getTempUrl(Integer userId, Integer fileId) throws Exception;

}
