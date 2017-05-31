package com.sspaas.sspaascloud.entity;

/**
 * 数据返回参数
 * 
 * @author 郭海英
 *
 */
public class DataResult {
	// 成功200
	public static final Integer OK = 200;
	
	public DataResult() {
	}

	public DataResult(Integer statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}

	private Integer statusCode;

	private String statusMsg;
	
	private Object dataList;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	public Object getDataList() {
		if (dataList == null){
			dataList = "";
		}
		return dataList;
	}

	public void setDataList(Object o) {
		this.dataList = o;
//		Gson gson = new Gson();  
//		this.json = gson.toJson(o);  
	}
	
	

}
