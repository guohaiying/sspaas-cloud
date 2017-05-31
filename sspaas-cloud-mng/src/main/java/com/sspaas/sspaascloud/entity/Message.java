package com.sspaas.sspaascloud.entity;

public class Message {
	// 手机号
	private String phone;
	// 短信模板编号
	private String templet;
	// 短信验证码
	private String[] phoneCode;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	public String[] getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String[] phoneCode) {
		this.phoneCode = phoneCode;
	}

}
