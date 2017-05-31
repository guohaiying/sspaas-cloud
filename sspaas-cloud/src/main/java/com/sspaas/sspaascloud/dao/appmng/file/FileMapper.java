package com.sspaas.sspaascloud.dao.appmng.file;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.sspaas.sspaascloud.entity.appmng.File;

public interface FileMapper {
	
	/**
	 * getFileType
	 * @Description:获取文件列表
	 * @author: 郭海英
	 */
	List<Map<String, Object>> getFileList(Map<String, Object> map);
	
	int insert(File record);

	File selectByPrimaryKey(Integer fileId);

	int updateByPrimaryKeySelective(File record);

	int updateByPrimaryKey(File record);
	int insertAndGetFileId(File record);
	/**
	 * @Title: findFileByOldName
	 * @Description:通过 oldName parentId type userId 查找文件
	 * @author: joker
	 * @Create:2016年12月13日下午4:03:10
	 * @param oldName
	 * @param parentId
	 * @return File
	 */
	File findFileByOldName(@Param("oldName")String oldName, @Param("parentId")Integer parentId, @Param("type")Short type,@Param("userId")Integer userId);

	/**
	 * @Title: findFilesByParentId 
	 * @Description:通过useriId和fileId查找文件夹下的文件
	 * @author: joker
	 * @Create:2016年12月14日下午6:51:57
	 * @param file
	 * @return List<File> 
	 */
	List<File> findFilesByParentId(File file);

	/**
	 * @Title: selectByFileIdAndUserID 
	 * @Description:通过用户id 和 文件id 查找文件
	 * @author: joker
	 * @Create:2016年12月20日下午3:33:23
	 * @param fileId
	 * @param userId
	 * @return File 
	 */
	File selectByFileIdAndUserID(@Param("fileId")Integer fileId, @Param("userId")Integer userId);
	
	/**
	 * deleteExceedSevenFile
	 * @Description:清除删除超过七天的文件
	 * @author: 郭海英
	 */
	Integer deleteExceedSevenFile(Map<String, Object> map);
	
	/**
	 * deleteExceedSevenFile
	 * @Description:清除删除超过七天的文件
	 * @author: 郭海英
	 */
	Integer deleteByPrimaryKey(int fileId);
}