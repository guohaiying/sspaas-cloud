package com.sspaas.sspaascloud.service.file;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface IFileService {

	/** 查找所有文件 */
	PageCrt findAllFile(PageCrt pageCrt);

	/** 查找所有上传文件的用户 */
	List<Map<String, Object>> getAllFileUser(PageCrt pageCrt);
	
	/** 根据userId查询用户树结构  */
	List<Map<String, Object>> getAllFileByUserId(Integer userId);

}
