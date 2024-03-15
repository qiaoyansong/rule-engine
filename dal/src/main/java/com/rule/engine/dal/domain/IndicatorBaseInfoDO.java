package com.rule.engine.dal.domain;

import java.util.Date;

public class IndicatorBaseInfoDO {

    /**
     * 表主键。
     */
    private Long id;

    /**
     * 指标名字。
     */
    private String indicatorName;

    /**
     * 指标描述。
     */
    private String indicatorDesc;

    /**
     * 指标返回类型。
     * #{@link com.rule.engine.api.enums.IndicatorValueTypeEnum}
     */
    private Integer indicatorReturnType;

    /**
     * 指标类型。
     * #{@link com.rule.engine.api.enums.IndicatorTypeEnum}
     */
    private Integer indicatorType;

    /**
     * 指标状态。
     * #{@link com.rule.engine.api.enums.IndicatorConfigStatusEnum}
     */
    private Integer status;

    /**
     * 创建者姓名。
     */
    private String creatorName;

    /**
     * 创建者ID.
     */
    private Long creatorId;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorDesc() {
        return indicatorDesc;
    }

    public void setIndicatorDesc(String indicatorDesc) {
        this.indicatorDesc = indicatorDesc;
    }

    public Integer getIndicatorReturnType() {
        return indicatorReturnType;
    }

    public void setIndicatorReturnType(Integer indicatorReturnType) {
        this.indicatorReturnType = indicatorReturnType;
    }

    public Integer getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(Integer indicatorType) {
        this.indicatorType = indicatorType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
        sb.append(", indicatorName=").append(indicatorName);
        sb.append(", indicatorDesc=").append(indicatorDesc);
        sb.append(", indicatorReturnType=").append(indicatorReturnType);
        sb.append(", indicatorType=").append(indicatorType);
        sb.append(", status=").append(status);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", creatorName=").append(creatorName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append("]");
        return sb.toString();
    }
}