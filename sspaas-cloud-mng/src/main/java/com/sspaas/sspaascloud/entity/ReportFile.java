package com.sspaas.sspaascloud.entity;

/**
 * @Name:ReportFile
 * @Description:举报文件pojo类
 * @author: joker
 * @Create:2016年12月26日下午11:14:48
 */
public class ReportFile {
	 	private Integer fileId;
	 	
	    private Integer userId;
	    
	    private String userName;
	    
	    private String oldName;
	    
	    private String uploadName;

	    private Long addTime;

	    /**   
	     * @Fields fileState : 文件状态
	     */  
	    private Short fileState;
	    
	    /**   
	     * @Fields userState : 用户状态   
	     */  
	    private Short userState;


	    /**   
	     * @Fields filePath : 文件下载路径 
	     */  
	    private String filePath;


		public Integer getFileId() {
			return fileId;
		}


		public void setFileId(Integer fileId) {
			this.fileId = fileId;
		}


		public Integer getUserId() {
			return userId;
		}


		public void setUserId(Integer userId) {
			this.userId = userId;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getOldName() {
			return oldName;
		}


		public void setOldName(String oldName) {
			this.oldName = oldName;
		}


		public String getUploadName() {
			return uploadName;
		}


		public void setUploadName(String uploadName) {
			this.uploadName = uploadName;
		}


		public Long getAddTime() {
			return addTime;
		}


		public void setAddTime(Long addTime) {
			this.addTime = addTime;
		}


		public Short getFileState() {
			return fileState;
		}


		public void setFileState(Short fileState) {
			this.fileState = fileState;
		}


		public Short getUserState() {
			return userState;
		}


		public void setUserState(Short userState) {
			this.userState = userState;
		}


		public String getFilePath() {
			return filePath;
		}


		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	    
	    
}
