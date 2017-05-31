package com.sspaas.sspaascloud.service.file;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface IFileTypeService {

	/** 查找所有文件类型 */
	PageCrt findAllFileType(PageCrt pageCrt);

	/** 查找所有文件类型根目录 */
	List<Map<String, Object>> getTypeRoot(PageCrt pageCrt);

	/** 添加文件类型 */
	Integer addType(PageCrt pageCrt, FileType filetype, MultipartFile file);

	/** 修改文件类型 */
	Integer updateType(PageCrt pageCrt, FileType filetype, MultipartFile file);
	
	/** 删除  */
	Integer deleteType(FileType filetype);

}
