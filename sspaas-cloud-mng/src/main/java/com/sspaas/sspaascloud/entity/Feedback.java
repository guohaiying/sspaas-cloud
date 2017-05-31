package com.sspaas.sspaascloud.entity;

public class Feedback {
    private Integer feedbackId;

    private Long userId;

    private String content;

    private Long addTime;

    private Short state;

    private Short type;
    
    private Short fState;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
    
    public Short getFState() {
        return fState;
    }

    public void setFState(Short fState) {
        this.fState = fState;
    }
}