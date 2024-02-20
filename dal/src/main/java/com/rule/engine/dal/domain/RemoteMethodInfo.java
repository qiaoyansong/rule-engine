package com.rule.engine.dal.domain;

import java.util.Date;

public class RemoteMethodInfo {
    private Long id;

    private String remoteMethodName;

    private Integer remoteMethodReturnType;

    private String remoteMethodDesc;

    private String argInfo;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemoteMethodName() {
        return remoteMethodName;
    }

    public void setRemoteMethodName(String remoteMethodName) {
        this.remoteMethodName = remoteMethodName;
    }

    public Integer getRemoteMethodReturnType() {
        return remoteMethodReturnType;
    }

    public void setRemoteMethodReturnType(Integer remoteMethodReturnType) {
        this.remoteMethodReturnType = remoteMethodReturnType;
    }

    public String getRemoteMethodDesc() {
        return remoteMethodDesc;
    }

    public void setRemoteMethodDesc(String remoteMethodDesc) {
        this.remoteMethodDesc = remoteMethodDesc;
    }

    public String getArgInfo() {
        return argInfo;
    }

    public void setArgInfo(String argInfo) {
        this.argInfo = argInfo;
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
        sb.append(", remoteMethodName=").append(remoteMethodName);
        sb.append(", remoteMethodReturnType=").append(remoteMethodReturnType);
        sb.append(", remoteMethodDesc=").append(remoteMethodDesc);
        sb.append(", argInfo=").append(argInfo);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append("]");
        return sb.toString();
    }
}