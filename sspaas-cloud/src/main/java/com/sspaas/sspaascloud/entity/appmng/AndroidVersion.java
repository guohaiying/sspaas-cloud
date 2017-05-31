package com.sspaas.sspaascloud.entity.appmng;

public class AndroidVersion {

	/**
	 * @Fields id : id
	 */
	private Integer id;
	/**
	 * @Fields version : 版本号
	 */
	private String version;

	/**
	 * @Fields updateTime : 更新时间
	 */
	private Long updateTime;

	/**
	 * @Fields Url : apk下载地址
	 */
	private String url;
	
	/**   
	 * @Fields state : 状态 (1、可用 ；2、不可用 )   
	 */  
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AndroidVersion [id=" + id + ", version=" + version + ", updateTime=" + updateTime + ", url=" + url
				+ "]";
	}

	
}
