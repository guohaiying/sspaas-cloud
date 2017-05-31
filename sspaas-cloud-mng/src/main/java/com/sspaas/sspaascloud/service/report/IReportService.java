package com.sspaas.sspaascloud.service.report;

import java.util.List;

import com.sspaas.sspaascloud.entity.FileType;
import com.sspaas.sspaascloud.entity.PageCrt;
import com.sspaas.sspaascloud.entity.ReportFile;

public interface IReportService {

	/**
	 * @Title: findAllReports 
	 * @Description:查詢所有舉報資源
	 * @author: joker
	 * @Create:2016年12月22日下午3:43:44
	 * @param pageCrt
	 * @return PageCrt 
	 */
	PageCrt findAllReports(PageCrt pageCrt);

	/**
	 * @Title: findAllFileType 
	 * @Description:查询所有文件类型
	 * @author: joker
	 * @Create:2016年12月26日下午4:33:47
	 * @return List<FileType> 
	 */
	List<FileType> findAllFileType();

	/**
	 * @Title: findReportFile 
	 * @Description:查找被举报文件
	 * @author: joker
	 * @Create:2016年12月26日下午11:09:54
	 * @return ReportFile 
	 */
	ReportFile findReportFile(Integer fileId) throws Exception;

	/**
	 * @Title: updateReportFile 
	 * @Description:处理举报信息 修改 文件 用户 举报 状态
	 * @author: joker
	 * @Create:2016年12月27日上午11:03:14
	 * @param fileId
	 * @param fileState
	 * @param userId
	 * @param userState
	 * @param reportId
	 * @param reportState void 
	 */
	void updateReportFile(Integer fileId, Short fileState, Integer userId, Short userState, Integer reportId,
			Short reportState);
	
}
