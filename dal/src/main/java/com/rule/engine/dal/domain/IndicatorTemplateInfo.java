package com.rule.engine.dal.domain;

import java.util.Date;

public class IndicatorTemplateInfo {
    private Long id;

    private Long indicatorId;

    private String rightValuePlaceHolder;

    private Integer rightValueInputType;

    private String judgeTypeList;

    private String rightValueList;

    private String interfaceName;

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

    public String getRightValuePlaceHolder() {
        return rightValuePlaceHolder;
    }

    public void setRightValuePlaceHolder(String rightValuePlaceHolder) {
        this.rightValuePlaceHolder = rightValuePlaceHolder;
    }

    public Integer getRightValueInputType() {
        return rightValueInputType;
    }

    public void setRightValueInputType(Integer rightValueInputType) {
        this.rightValueInputType = rightValueInputType;
    }

    public String getJudgeTypeList() {
        return judgeTypeList;
    }

    public void setJudgeTypeList(String judgeTypeList) {
        this.judgeTypeList = judgeTypeList;
    }

    public String getRightValueList() {
        return rightValueList;
    }

    public void setRightValueList(String rightValueList) {
        this.rightValueList = rightValueList;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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
        sb.append(", rightValuePlaceHolder=").append(rightValuePlaceHolder);
        sb.append(", rightValueInputType=").append(rightValueInputType);
        sb.append(", judgeTypeList=").append(judgeTypeList);
        sb.append(", rightValueList=").append(rightValueList);
        sb.append(", interfaceName=").append(interfaceName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append("]");
        return sb.toString();
    }
}