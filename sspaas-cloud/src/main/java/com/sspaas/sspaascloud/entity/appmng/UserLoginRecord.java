package com.sspaas.sspaascloud.entity.appmng;

public class UserLoginRecord {
    private Integer userRecordId;

    private Integer userId;

    private Long loginTime;

    private Long quitTime;

    private String loginPlace;

    private String loginIp;

    private Integer equipment;

    private String uuid;

    public Integer getEquipment() {
		return equipment;
	}

	public void setEquipment(Integer equipment) {
		this.equipment = equipment;
	}

	public Integer getUserRecordId() {
        return userRecordId;
    }

    public void setUserRecordId(Integer userRecordId) {
        this.userRecordId = userRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Long quitTime) {
        this.quitTime = quitTime;
    }

    public String getLoginPlace() {
        return loginPlace;
    }

    public void setLoginPlace(String loginPlace) {
        this.loginPlace = loginPlace == null ? null : loginPlace.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
}