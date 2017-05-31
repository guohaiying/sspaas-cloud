package com.sspaas.sspaascloud.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportFile;

public interface ReportMapper {

	/**
	 * @Title: findAllReportsCount 
	 * @Description:查詢总数量
	 * @author: joker
	 * @Create:2016年12月22日下午3:49:53
	 * @param pageCrt
	 * @return Integer 
	 */
	Integer findAllReportsCount(PageCrt pageCrt);

	/**
	 * @Title: findAllReports 
	 * @Description:查询所有举报资源
	 * @author: joker
	 * @Create:2016年12月22日下午3:51:04
	 * @param pageCrt
	 * @return List<?> 
	 */
	List<?> findAllReports(PageCrt pageCrt);

	/**
	 * @Title: findAllFileType 
	 * @Description:查询所有文件类型
	 * @author: joker
	 * @Create:2016年12月26日下午4:34:51
	 * @return List<FileType> 
	 */
	List<FileType> findAllFileType();

	ReportFile findReportFile(Integer fileId);

	/**
	 * @Title: updateFileState 
	 * @Description:更新文件状态
	 * @author: joker
	 * @Create:2016年12月27日上午11:15:47
	 * @param fileId
	 * @param fileState
	 * @return Integer 
	 */
	Integer updateFileState(@Param("fileId") Integer fileId,@Param("fileState") Short fileState);

	/**
	 * @Title: updateUserState 
	 * @Description:更新用户状态
	 * @author: joker
	 * @Create:2016年12月27日上午11:16:03
	 * @param userId
	 * @param userState
	 * @return Integer 
	 */
	Integer updateUserState(@Param("userId") Integer userId, @Param("userState")Short userState);

	/**
	 * @Title: updateReportState 
	 * @Description:更新举报状态
	 * @author: joker
	 * @Create:2016年12月27日上午11:16:16
	 * @param reportId
	 * @param reportState
	 * @return Integer 
	 */
	Integer updateReportState(@Param("reportId")Integer reportId, @Param("reportState")Short reportState);
	
	
}