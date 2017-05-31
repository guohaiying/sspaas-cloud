package com.sspaas.sspaascloud.dao.appmng.file;

import java.util.List;
import java.util.Map;

public interface FileTypeMapper {
	
	/**
	 * getFileTypeList
	 * @Description:获取文件类型列表
	 * @author: 郭海英
	 */
	List<Map<String,Object>> getFileTypeList();
	
    Integer selectByName(String name);

	Integer getParentTypeId(Integer fileTypeId);
}