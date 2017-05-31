package com.sspaas.sspaascloud.service.appmng.file.impl;


import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.dao.appmng.file.FileMapper;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IDownloadFileService;
import com.sspaas.sspaascloud.util.swift.SwiftUploadPojo;
import com.sspaas.sspaascloud.util.swift.SwiftUploadUtils;

/**
 * @Name:DownloadFileService
 * @Description:下载文件
 * @author: joker
 * @Create:2016年12月6日上午10:27:18
 */
@Service
public class DownloadFileService implements IDownloadFileService {
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private SwiftUploadPojo swiftUploadPojo;

	@Override
	public DLPayload downloadFile(Integer userId, Integer fileId) {
		// TODO Auto-generated method stub
		File file = fileMapper.selectByPrimaryKey(fileId);
		String uploadName = file.getUploadName();
		
		//获得os
		OSClientV3 osV3 = SwiftUploadUtils.unscopedAuthentication(swiftUploadPojo);
		//获取swift中保存的对象
		SwiftObject swiftObject = osV3.objectStorage().objects().get(userId.toString(),uploadName);
		
		return swiftObject.download();
	}

	@Override
	public String getTempUrl(Integer userId, Integer fileId) throws Exception {
		//验证用户id 和文件id是否匹配
		File file = fileMapper.selectByFileIdAndUserID(fileId,userId);
		if (file!=null) {
			//生成临时url
			swiftUploadPojo.setContainerName(userId.toString());
			return SwiftUploadUtils.getTempUrl(swiftUploadPojo, file.getUploadName(), file.getOldName());
			
		}else {
			
			return null;
		}
		
		
		
	}

}
