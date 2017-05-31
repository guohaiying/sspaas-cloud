package com.sspaas.sspaascloud.service.appmng.file.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sspaas.sspaascloud.common.FileCommon;
import com.sspaas.sspaascloud.dao.appmng.file.FileMapper;
import com.sspaas.sspaascloud.dao.appmng.file.FileTypeMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.service.appmng.file.IUploadFileService;

/**
 * @Name:UploadFileService
 * @Description:上传文件
 * @author: joker
 * @Create:2016年12月5日下午2:12:43
 */
@Service
public class UploadFileService implements IUploadFileService {
	@Autowired
	private FileMapper fileMapper;
	@Autowired
	private FileTypeMapper fileTypeMapper;
	
	@Autowired
	private UserCapacityMapper userCapacityMapper;

	@Transactional
	@Override
	public DataResult uploadFile(Integer userId, File file, Integer fileId,String fileContextType) {
		DataResult dataResult = new DataResult();
		try {
			
			file.setUserId(userId);
			
			file.setParentId(fileId);
			
			// 判断文件类型
			if (fileContextType != null) {
				// 设置文件类型
				Integer fileTypeId = fileTypeMapper.selectByName(fileContextType);
				if (fileTypeId == null) {
					file.setFileTypeId(FileCommon.FILE_TYPE_OTHER_ID);
				} else {
					file.setFileTypeId(fileTypeId);
				}
			}
			file.setState(FileCommon.FILE_STATE_NORMAL);
			file.setType(FileCommon.FILE_TYPE_FILE);
			// 保存文件信息到数据库
			fileMapper.insert(file);
			// 查询用户已用容量
			Map<String, Object> userCapacity = userCapacityMapper.userCapacity(userId);
			Float userdCapacity = (Float) userCapacity.get("userdCapacity");
			userdCapacity = userdCapacity + (float) file.getSize() / (1024 * 1024);
			// 修改用户已用空间
			userCapacityMapper.updateCapacity(userId, userdCapacity);
			// 查询filetype父id
			file.setFileTypeId(fileTypeMapper.getParentTypeId(file.getFileTypeId()));
			dataResult.setDataList(file);
			dataResult.setStatusCode(DataResult.OK);
			dataResult.setStatusMsg("文件上传成功");
		} catch (Exception e) {
			dataResult.setStatusCode(500);
			dataResult.setStatusMsg("文件上传失败");
			return dataResult;
		}

		return dataResult;
	}

	@Override
	public Boolean findUserCapacity(Integer userId, long size) throws Exception {
		// 转换单位
		Float sizeM = (float) size / (1024 * 1024);
		Map<String, Object> userCapacity = userCapacityMapper.userCapacity(userId);
		Long totalCapacity = (long)userCapacity.get("totalCapacity");
		Float userdCapacity = (float) userCapacity.get("userdCapacity");

		if ((userdCapacity + sizeM) >  (float)totalCapacity) {
			return false;
		}

		return true;
	}

}
