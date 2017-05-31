package com.sspaas.sspaascloud.service.version.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sspaas.sspaascloud.dao.version.AndroidVersionMapper;
import com.sspaas.sspaascloud.entity.AndroidVersion;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.service.version.IAndroidVersionService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;


@Service
public class AndroidVersionService implements IAndroidVersionService {
	@Autowired
	AndroidVersionMapper androidVersionMapper;
	
	@Autowired
	private SwiftUploadPojo uploadPojo;


	@Override
	public PageCrt versionList(PageCrt page) {
        int rows = page.getRows();
        int pag = page.getPage();
        Integer records=androidVersionMapper.total(page);
        page.setRecords(records);
        page.setTotal(records % rows == 0 ? records / rows : records / rows + 1);
        int index = ((pag - 1) * rows);
        page.setIndex(index);
        List<Map<String, Object>> list = androidVersionMapper.selectVersionList(page);
        page.setList(list);
        return page;
	}

	@Override
	public Integer editVersion(AndroidVersion version, MultipartFile file) {
		if(file!=null){
			// 先上传头像
			uploadPojo.setMultipartFile(file);
			uploadPojo.setContainerName("capacity");
			String object = SwiftUploadUtils.createObject(uploadPojo);
			version.setUrl(object);
		}
		version.setUpdateTime(new Date().getTime());
		return androidVersionMapper.editVersion(version);
	}

	@Override
	public Integer addVersion(AndroidVersion version, MultipartFile file) {
		// 先上传
		uploadPojo.setMultipartFile(file);
		uploadPojo.setContainerName("capacity");
		String object = SwiftUploadUtils.createObject(uploadPojo);
		version.setUrl(object);
		
		return androidVersionMapper.addVersion(version);
	}

	@Override
	public Integer deleteVersion(int id) {
		return androidVersionMapper.deleteVersion(id);
	}
	
}
