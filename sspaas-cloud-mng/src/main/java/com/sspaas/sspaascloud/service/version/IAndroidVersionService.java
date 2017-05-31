package com.sspaas.sspaascloud.service.version;

import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.entity.AndroidVersion;
import com.sspaas.sspaascloud.entity.PageCrt;

public interface IAndroidVersionService {

	PageCrt versionList(PageCrt page);
	
	Integer editVersion(AndroidVersion version,MultipartFile file);
	
	Integer addVersion(AndroidVersion version, MultipartFile file);
	
	Integer deleteVersion(int id);
}
