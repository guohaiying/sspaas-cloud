package com.sspaas.sspaascloud.entity.appmng;

public class UserPurchaseCapacity {
    private Integer id;

    private Integer userId;

    private Integer capacityId;

    private Long addTime;

    private Long payTime;

    private Long matureTime;

    private Double price;

    private Short state;
    
    private Short payState;
    
    private Short channel;
    
    private String note;
    
    private String orderNumber;
    
    public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Short getPayState() {
		return payState;
	}

	public void setPayState(Short payState) {
		this.payState = payState;
	}

	public Short getChannel() {
		return channel;
	}

	public void setChannel(Short channel) {
		this.channel = channel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(Integer capacityId) {
        this.capacityId = capacityId;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getMatureTime() {
        return matureTime;
    }

    public void setMatureTime(Long matureTime) {
        this.matureTime = matureTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
    
}