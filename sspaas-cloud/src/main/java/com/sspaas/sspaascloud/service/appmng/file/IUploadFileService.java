package com.sspaas.sspaascloud.service.appmng.file;

import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;

/**
 * @Name:IUploadFileService
 * @Description:上传文件
 * @author: joker
 * @Create:2016年12月5日上午10:47:37
 */
public interface IUploadFileService {

	/**
	 * @Name:uploadFile
	 * @Description:上传文件
	 * @author: joker
	 * @Create:2016年12月5日上午10:48:12
	 * @param userId
	 * @param file
	 * @return DataResult
	 */
	DataResult uploadFile(Integer userId, File file, Integer flieId,String fileContextType);

	/**
	 * @Title: findUserCapacity
	 * @Description:查询用户容量是否够用
	 * @author: joker
	 * @Create:2017年1月7日上午11:31:09
	 * @param userId
	 *            用户id
	 * @param size
	 *            上传文件大小
	 * @return Boolean
	 */
	Boolean findUserCapacity(Integer userId, long size) throws Exception;

}
