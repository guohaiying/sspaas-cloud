package com.sspaas.sspaascloud.entity;

public class UserCapacity {
    private Integer userId;

    private String totalCapacity;

    private String userdCapacity;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity == null ? null : totalCapacity.trim();
    }

    public String getUserdCapacity() {
        return userdCapacity;
    }

    public void setUserdCapacity(String userdCapacity) {
        this.userdCapacity = userdCapacity == null ? null : userdCapacity.trim();
    }
}