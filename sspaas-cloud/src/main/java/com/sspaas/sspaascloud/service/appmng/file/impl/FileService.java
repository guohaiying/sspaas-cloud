package com.sspaas.sspaascloud.service.appmng.file.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sspaas.sspaascloud.common.FileCommon;
import com.sspaas.sspaascloud.controller.appmng.user.UserController;
import com.sspaas.sspaascloud.dao.appmng.file.FileMapper;
import com.sspaas.sspaascloud.dao.appmng.file.FileTypeMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;
import com.sspaas.sspaascloud.entity.appmng.FileVo;
import com.sspaas.sspaascloud.service.appmng.file.IFileService;

/**
 * @Name:FileService
 * @Description:文件service
 * @author: joker
 * @Create:2016年12月6日下午2:06:09
 */
@Service
public class FileService implements IFileService {

	/** 日志记录器 */
	Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private FileMapper fileMapper;

	@Autowired
	private FileTypeMapper fileTypeMapper;

	@Autowired
	private UserCapacityMapper userCapacityMapper;

	@Override
	public File getFile(Integer fileId) {
		return fileMapper.selectByPrimaryKey(fileId);
	}

	/**
	 * getFileTypeList
	 * 
	 * @Description:获取文件类型列表
	 * @author: 郭海英
	 */
	@Override
	public DataResult getFileTypeList() {
		DataResult result = new DataResult();
		try {
			List<Map<String, Object>> list = fileTypeMapper.getFileTypeList();
			result.setDataList(list);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.debug("查询文件列表发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * getFileType
	 * @Description:获取文件列表
	 * @author: 郭海英
	 */
	@Override
	public DataResult getFileList(Map<String, Object> map) {
		DataResult result = new DataResult();
		try {
			if (map == null || map.get("userId") == null) {
				result.setStatusCode(500);
				result.setStatusMsg("请求参数不合法");
				log.debug("用户文件列表参数为空  userId:" + map.get("userId") + " parentId:" + map.get("parentId"));
				return result;
			}
			
			//清除回收站超过7天的文件  如果为回收站列表
			if(Integer.parseInt(map.get("state")+"")==2){
				Integer rel = fileMapper.deleteExceedSevenFile(map);
				if(rel<0){
					log.debug("清除回收站超过七天文件失败 userId:"+map.get("userId"));
				}
			}
			
			List<Map<String, Object>> list = fileMapper.getFileList(map);
			result.setDataList(list);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("获取成功");
		} catch (Exception e) {
			result.setStatusCode(500);
			result.setStatusMsg("服务器繁忙");
			log.error("查询个人信息发生异常，异常信息如下：");
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public DataResult saveFile(File file) {
		DataResult result = new DataResult();
		try {
			fileMapper.insertAndGetFileId(file);
			result.setStatusCode(DataResult.OK);
			result.setStatusMsg("文件夹创建成功");
			FileVo fileVo = new FileVo();
			fileVo.setFileId(file.getFileId());
			fileVo.setAddTime(file.getAddTime());
			fileVo.setName(file.getOldName());
			fileVo.setParentId(file.getParentId());
			fileVo.setType(file.getType());
			result.setDataList(fileVo);
		} catch (Exception e) {

			result.setStatusCode(500);
			result.setStatusMsg("文件夹创建失败");
			return result;
		}

		return result;
	}

	@Override
	public void removeFile(List<File> files) throws Exception {
		Long date = new Date().getTime();
		for (File file : files) {
			if (file.getFileId() != null && file.getUserId() != null && file.getParentId() != null) {
				file.setUpdateTime(date);
				//查询文件名是否重复
				String fileName = searchFileName(file);
				file.setOldName(fileName);
				fileMapper.updateByPrimaryKeySelective(file);
			} else {
				throw new RuntimeException("参数不完整");
			}
		}
	}

	@Override
	public Boolean findFileByOldName(File file) {

		File findFile = fileMapper.findFileByOldName(file.getOldName(), file.getParentId(), file.getType(),
				file.getUserId());
		if (findFile != null) {// 文件名存在
			return true;
		}
		return false;// 文件名不存在
	}

	@Override
	public void deleteFile(File file) throws Exception {
		// 判断是文件还是文件夹
		file.setState(FileCommon.FILE_STATE_DELETE);
		file.setDeleteTime(new Date().getTime());
		if (file.getType() == FileCommon.FILE_TYPE_FOLDER) {// 文件夹
			// 查找文件夹下的文件
			List<File> files = fileMapper.findFilesByParentId(file);
			if (!files.isEmpty()) {
				for (File subfile : files) {
					deleteFile(subfile);
				}
			}
		}
		fileMapper.updateByPrimaryKeySelective(file);
		// 更新用户容量
		File file2 = fileMapper.selectByFileIdAndUserID(file.getFileId(), file.getUserId());
		if (file2.getSize() > 0) {
			// 获取用户已用容量
			Map<String, Object> userCapacity = userCapacityMapper.userCapacity(file.getUserId());
			Float userdCapacity = (Float) userCapacity.get("userdCapacity");
			userdCapacity = userdCapacity - (float) file2.getSize() / (1024 * 1024);
			if (userdCapacity<0) {
				userdCapacity = 0F;
				
			}
			
			// 修改用户已用空间
			userCapacityMapper.updateCapacity(file.getUserId(), userdCapacity);
		}
	}
	
	@Override
	public void deleteRecycleBin(File file) throws Exception {
		// 判断是文件还是文件夹
		file.setState(FileCommon.FILE_STATE_DELETE);
		file.setDeleteTime(new Date().getTime());
		if (file.getType() == FileCommon.FILE_TYPE_FOLDER) {// 文件夹
			// 查找文件夹下的文件
			List<File> files = fileMapper.findFilesByParentId(file);
			if (!files.isEmpty()) {
				for (File subfile : files) {
					deleteRecycleBin(subfile);
				}
			}
		}
		fileMapper.deleteByPrimaryKey(file.getFileId());
	}
	
	@Override
	public void regainFile(File file) throws Exception{
		// 判断是文件还是文件夹
		file.setState(FileCommon.FILE_STATE_NORMAL);
		file.setDeleteTime(null);
		file.setUpdateTime(new Date().getTime());
		if (file.getType() == FileCommon.FILE_TYPE_FOLDER) {// 文件夹
			// 查找文件夹下的文件
			List<File> files = fileMapper.findFilesByParentId(file);
			if (!files.isEmpty()) {
				for (File subfile : files) {
					regainFile(subfile);
				}
			}
		}
		fileMapper.updateByPrimaryKeySelective(file);
		// 更新用户容量
		File file2 = fileMapper.selectByFileIdAndUserID(file.getFileId(), file.getUserId());
		if (file2.getSize() > 0) {
			// 获取用户已用容量
			Map<String, Object> userCapacity = userCapacityMapper.userCapacity(file.getUserId());
			Float userdCapacity = (Float) userCapacity.get("userdCapacity");
			userdCapacity = userdCapacity + (float) file2.getSize() / (1024 * 1024);
			if (userdCapacity<0) {
				userdCapacity = 0F;
			}
			// 修改用户已用空间
			userCapacityMapper.updateCapacity(file.getUserId(), userdCapacity);
		}
	}


	@Override
	public void updateFile(File file) throws Exception {
		fileMapper.updateByPrimaryKeySelective(file);

	}
	/**
	 * @Title: searchFileName
	 * @Description:查找可用文件名
	 * @author: joker
	 * @Create:2016年12月13日下午5:11:50
	 * @param oldName
	 * @param parentId
	 * @param type
	 * @return String
	 */
	@Override
	public String searchFileName(File file) {
		// 查找在同一文件夹下有没有相同的文件名
		// 新建文件夹(123)
		while (findFileByOldName(file)) { // 存在相同的名字
			String name = file.getOldName();

			// 判断是文件夹名还是文件名
			if (file.getType() == FileCommon.FILE_TYPE_FILE) {
				// 文件名.jpg
				int i = name.lastIndexOf(".");
				// 判断是否有扩展名
				if (i > 0) {
					name = name.substring(0, i);
				}
			}
			// 获取文件中最后包含(123)的
			Pattern pattern = Pattern.compile("\\([0-9]*\\)");
			Matcher matcher = pattern.matcher(name);
			String num = "";
			while (matcher.find()) {
				num = matcher.group(0);
			}
			// 判断是否含(123) 截取出其中的数字
			if (!num.equals("")) { // 含有(123)
				// 判断(123) 是否在文件最后
				if (name.substring(name.lastIndexOf(num)).equals(num)) {
					name = name.substring(0, name.length() - num.length());
					num = num.replace("(", "");
					num = num.replace(")", "");
					name += "(" + (Integer.parseInt(num) + 1) + ")";
				}

			} else {
				name += "(1)";
			}
			// 如果是文件拼接扩展名
			if (file.getType() == FileCommon.FILE_TYPE_FILE) {
				// 文件名.jpg
				int i = file.getOldName().lastIndexOf(".");
				// 判断是否有扩展名
				if (i > 0) {
					name += file.getOldName().substring(i);
				}
			}

			file.setOldName(name);
		}

		return file.getOldName();

	}
}
