package com.sspaas.sspaascloud.service.file.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.file.FileMapper;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.file.IFileService;

@Service
public class FileServiceImpl implements IFileService {

	Logger log = Logger.getLogger(FileServiceImpl.class);

	@Resource
	private FileMapper fileMapper;

	/** 查找所有文件*/
	@Override
	public PageCrt findAllFile(PageCrt pageCrt) {
		Integer records = fileMapper.findAllFileCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list = fileMapper.findAllFile(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}

	/** 查找所有上传文件的用户 */
	@Override
	public List<Map<String, Object>> getAllFileUser(PageCrt pageCrt) {
		List<Map<String, Object>> map = fileMapper.getAllFileUser(pageCrt);
		return map;
	}
	
	/** 根据userId查询用户树结构  */
	@Override
	public List<Map<String, Object>> getAllFileByUserId(Integer userId) {
		List<Map<String, Object>> map = fileMapper.getAllFileByUserId(userId);
		return map;
	}
	
	


}
