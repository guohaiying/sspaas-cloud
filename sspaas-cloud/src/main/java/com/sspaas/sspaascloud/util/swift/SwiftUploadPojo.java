package com.sspaas.sspaascloud.util.swift;
/*
 * swift文件上传参数类
 */


import org.springframework.web.multipart.MultipartFile;

public class SwiftUploadPojo {
	/**
	 * 域名
	 */
	private String domainName;
	/**   
	 * @Fields domainId :domainId
	 */  
	private String projectId;
	/*
	 * 用户名
	 */
	private String userName;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * v3验证
	 */
	private String endPointUrl; 
	/*
	 * 仓库名
	 */
	private String containerName;
	/*
	 * 输入流
	 */
	private MultipartFile multipartFile;
	
	/**   
	 * @Fields secretKey : swift 账户秘钥
	 */  
	private String secretKey;
	
	/**   
	 * @Fields tempUrlPref : 临时url前缀  
	 */  
	private String tempUrlPref;
	
	/**   
	 * @Fields expiresTime : 临时url可用时长
	 */  
	private Long expiresTime;
	/**
	 * 容器访问地址
	 * @return
	 */
	private String swiftUrl;
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTempUrlPref() {
		return tempUrlPref;
	}
	public void setTempUrlPref(String tempUrlPref) {
		this.tempUrlPref = tempUrlPref;
	}
	public Long getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(Long expiresTime) {
		this.expiresTime = expiresTime;
	}
	public String getSwiftUrl() {
		return swiftUrl;
	}
	public void setSwiftUrl(String swiftUrl) {
		this.swiftUrl = swiftUrl;
	}
	
	
	
	
}
