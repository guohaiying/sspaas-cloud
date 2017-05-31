package com.sspaas.sspaascloud.service.appmng.file;

import java.util.List;
import java.util.Map;

import com.sspaas.sspaascloud.entity.appmng.DataResult;
import com.sspaas.sspaascloud.entity.appmng.File;

public interface IFileService {

	/**
	 * @Name:getFile
	 * @Description:通过用户id和文件id获取文件详细信息
	 * @author: joker
	 * @Create:2016年12月6日下午2:06:44
	 * @param userId
	 * @param fileId
	 * @return
	 */
	File getFile(Integer fileId);
	
	/**
	 * getFileTypeList
	 * @Description:获取文件类型列表
	 * @author: 郭海英
	 */
	DataResult getFileTypeList();
	
	/**
	 * getFileTypeList
	 * @Description:获取文件类型列表
	 * @author: 郭海英
	 */
	DataResult getFileList(Map<String,Object> map);

	/**
	 * @Title: saveFile
	 * @Description:新建文件夹
	 * @author: joker
	 * @Create:2016年12月13日上午11:31:18
	 * @param file
	 * @return DataResult
	 */
	DataResult saveFile(File file);

	/**
	 * @Title: removeFile
	 * @Description:移动文件
	 * @author: joker
	 * @Create:2016年12月13日下午2:31:42
	 * @param file
	 * @return DataResult
	 */
	void removeFile(List<File> files) throws Exception;
	
	/**
	 * @Title: updateFile 
	 * @Description:更新文件信息
	 * @author: joker
	 * @Create:2016年12月30日下午6:08:14
	 * @param file
	 * @throws Exception void 
	 */
	void updateFile(File file) throws Exception;

	/**
	 * @Title: findFileByOldName
	 * @Description:查看文件名是否已存在
	 * @author: joker
	 * @Create:2016年12月13日下午3:58:43
	 * @param oldName
	 * @param parentId
	 * @return Boolean true 存在 false 不存在
	 */
	Boolean findFileByOldName(File file);

	/**
	 * @Title: deleteFile 
	 * @Description:通过id删除文件(更改文件状态)
	 * @author: joker
	 * @Create:2016年12月14日下午5:51:56
	 * @param integer
	 * @throws Exception void 
	 */
	void deleteFile(File file)throws Exception;

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
	String searchFileName(File file);
	
	/**
	 * @Title: regainFile
	 * @Description:还原文件或文件夹
	 * @author: joker
	 * @Create:2016年12月13日下午5:11:50
	 * @param file
	 * @return void
	 */
	void regainFile(File file) throws Exception;
	
	/**
	 * @Title: 
	 * @Description:删除回收站文件
	 * @author: joker
	 * @Create:2016年12月13日下午5:11:50
	 * @param file
	 * @return void
	 */
	void deleteRecycleBin(File file) throws Exception;

}
