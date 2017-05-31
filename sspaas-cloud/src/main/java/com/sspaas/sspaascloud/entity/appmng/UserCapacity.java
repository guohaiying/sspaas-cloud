package com.sspaas.sspaascloud.entity.appmng;

public class UserCapacity {
    private Integer userId;

    private Long totalCapacity;

    private float userdCapacity;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public Long getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Long totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public float getUserdCapacity() {
		return userdCapacity;
	}

	public void setUserdCapacity(float userdCapacity) {
		this.userdCapacity = userdCapacity;
	}
}