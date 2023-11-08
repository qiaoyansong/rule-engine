package com.rule.engine.dal.domain;

import java.util.Date;

public class EventBaseInfoDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 事件来源
     * #{@link com.rule.engine.api.enums.EventSourceEnum}
     */
    private Integer eventSource;

    /**
     * 事件标识 MQ时为topic, 接口时为全限定名
     */
    private String eventIdentifier;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 事件说明
     */
    private String eventDesc;

    /**
     * 事件说明
     */
    private String eventTemplate;

    private Date gmtCreate;

    private Date gmtModify;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人名字
     */
    private String operatorName;

    /**
     * 校验脚本
     */
    private String checkScript;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventSource() {
        return eventSource;
    }

    public void setEventSource(Integer eventSource) {
        this.eventSource = eventSource;
    }

    public String getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(String eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventTemplate() {
        return eventTemplate;
    }

    public void setEventTemplate(String eventTemplate) {
        this.eventTemplate = eventTemplate;
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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCheckScript() {
        return checkScript;
    }

    public void setCheckScript(String checkScript) {
        this.checkScript = checkScript;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eventSource=").append(eventSource);
        sb.append(", eventIdentifier=").append(eventIdentifier);
        sb.append(", eventName=").append(eventName);
        sb.append(", eventDesc=").append(eventDesc);
        sb.append(", eventTemplate=").append(eventTemplate);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", checkScript=").append(checkScript);
        sb.append("]");
        return sb.toString();
    }
}