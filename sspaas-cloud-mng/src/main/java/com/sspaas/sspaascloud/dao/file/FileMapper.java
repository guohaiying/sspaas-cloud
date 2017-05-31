package com.sspaas.sspaascloud.dao.file;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.PageCrt;

public interface FileMapper {
	// 查询所有文件类型
	List<?> findAllFile(PageCrt pageCrt);

	// 查找所有文件类型数量
	Integer findAllFileCount(PageCrt pageCrt);

	// 查找所有上传文件的用户
	List<Map<String, Object>> getAllFileUser(PageCrt pageCrt);
	
	// 根据userId查询用户树结构 
	List<Map<String, Object>> getAllFileByUserId(Integer userId);
	
	

}