package com.sspaas.sspaascloud.entity.appmng;

public class FeedbackType {
    private Integer feedbackTypeId;

    private String name;

    private String descript;

    private Long addTime;

    public Integer getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Integer feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}