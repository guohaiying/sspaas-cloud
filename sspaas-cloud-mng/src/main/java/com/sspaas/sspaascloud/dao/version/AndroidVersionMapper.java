package com.sspaas.sspaascloud.dao.version;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.AndroidVersion;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface AndroidVersionMapper {
	
	Integer total(PageCrt page);
	
	List<Map<String, Object>> selectVersionList(PageCrt page);
	
	Integer editVersion(AndroidVersion version);
	
	Integer addVersion(AndroidVersion version);
	
	Integer deleteVersion(int id);

}
