package com.sspaas.sspaascloud.dao.file;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface FileTypeMapper {

	// 查询所有文件类型
	List<?> findAllFileType(PageCrt pageCrt);

	// 查找所有文件类型数量
	Integer findAllFileTypeCount(PageCrt pageCrt);
	
	// 查询所有文件类型根目录
	List<Map<String,Object>> getTypeRoot();
	
	// 添加
	Integer addType(FileType filetype);
	
	// 修改
	Integer updateType(FileType filetype);
	
	// 删除
	Integer deleteType(FileType filetype);
}