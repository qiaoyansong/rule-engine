package com.rule.engine.api.enums;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:24 下午
 * description：
 */
public enum IndicatorConfigStatusEnum {

    STATUS_ACTIVE(1, "active", "发布"),
    STATUS_INACTIVE(2, "inactive", "禁用");

    private int code;
    private String desc;
    private String descZH;

    private IndicatorConfigStatusEnum(int code, String desc, String descZH) {
        this.code = code;
        this.desc = desc;
        this.descZH = descZH;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescZH() {
        return this.descZH;
    }

    public void setDescZH(String descZH) {
        this.descZH = descZH;
    }

    public static IndicatorConfigStatusEnum valueOf(int status) {
        IndicatorConfigStatusEnum[] indicatorConfigStatusEna = values();
        IndicatorConfigStatusEnum[] var2 = indicatorConfigStatusEna;
        int var3 = indicatorConfigStatusEna.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            IndicatorConfigStatusEnum configStatusEnum = var2[var4];
            if (configStatusEnum.getCode() == status) {
                return configStatusEnum;
            }
        }

        throw new IllegalArgumentException("Can not found config status enum for code:" + status);
    }
}
