package com.rule.engine.dal.domain;

import java.util.Date;

public class IndicatorRuleEventParsingInfo {

    /**
     * 事件解析规则id
     */
    private Long id;

    /**
     * 关联指标id
     */
    private Long indicatorId;

    /**
     * 事件id
     */
    private Long eventId;

    /**
     * 解析的字段名称
     */
    private String marshallField;

    /**
     * 状态
     * #{@link com.rule.engine.api.enums.IndicatorConfigStatusEnum}
     */
    private Integer status;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getMarshallField() {
        return marshallField;
    }

    public void setMarshallField(String marshallField) {
        this.marshallField = marshallField;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", indicatorId=").append(indicatorId);
        sb.append(", eventId=").append(eventId);
        sb.append(", marshallField=").append(marshallField);
        sb.append(", status=").append(status);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append("]");
        return sb.toString();
    }
}