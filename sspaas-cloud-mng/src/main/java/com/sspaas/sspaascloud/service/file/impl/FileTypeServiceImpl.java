package com.sspaas.sspaascloud.service.file.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.dao.file.FileTypeMapper;
import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.file.IFileTypeService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

@Service
public class FileTypeServiceImpl implements IFileTypeService {

	Logger log = Logger.getLogger(FileTypeServiceImpl.class);

	@Resource
	private FileTypeMapper fileTypeMapper;

	@Autowired
	private SwiftUploadPojo uploadPojo;

	/** 查找所有文件类型 */
	@Override
	public PageCrt findAllFileType(PageCrt pageCrt) {
		Integer records = fileTypeMapper.findAllFileTypeCount(pageCrt);
		pageCrt.setRecords(records);
		List<?> list = fileTypeMapper.findAllFileType(pageCrt);
		pageCrt.setList(list);
		return pageCrt;
	}

	/** 查找所有文件类型根目录 */
	@Override
	public List<Map<String, Object>> getTypeRoot(PageCrt pageCrt) {
		List<Map<String, Object>> list = fileTypeMapper.getTypeRoot();
		return list;
	}

	/** 添加文件类型 */
	@Override
	public Integer addType(PageCrt pageCrt, FileType filetype, MultipartFile file) {

		// 先上传头像
		uploadPojo.setMultipartFile(file);
		uploadPojo.setContainerName("fileType");
		String object = SwiftUploadUtils.createObject(uploadPojo);
		
		filetype.setAddTime(new Date().getTime());
		filetype.setIcon(object);
		Integer result = fileTypeMapper.addType(filetype);
		return result;
	}

	/** 修改文件类型 */
	@Override
	public Integer updateType(PageCrt pageCrt, FileType filetype, MultipartFile file) {
		if(file.getSize()>0){
			// 先上传头像
			uploadPojo.setMultipartFile(file);
			uploadPojo.setContainerName("fileType");
			String object = SwiftUploadUtils.createObject(uploadPojo);
			filetype.setIcon(object);
		}
		Integer result = fileTypeMapper.updateType(filetype);
		return result;
	}
	
	/** 删除 */
	@Override
	public Integer deleteType(FileType filetype) {
		Integer result = fileTypeMapper.deleteType(filetype);
		return result;
	}

}
