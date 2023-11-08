package com.rule.engine.api.enums;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:27 下午
 * description：
 */
public enum IndicatorValueTypeEnum {
    TYPE_LONG(1, "long", Long.class.getCanonicalName()),
    TYPE_INTEGER(2, "integer", Integer.class.getCanonicalName()),
    TYPE_BYTE(4, "byte", Byte.class.getCanonicalName()),
    TYPE_BOOLEAN(8, "boolean", Boolean.class.getCanonicalName()),
    TYPE_BIG_DECIMAL(16, "big-decimal", BigDecimal.class.getCanonicalName()),
    TYPE_FLOAT(32, "float", Float.class.getCanonicalName()),
    TYPE_DOUBLE(64, "double", Double.class.getCanonicalName()),
    TYPE_TEXT(128, "text", String.class.getCanonicalName()),
    TYPE_LIST(256, "list", List.class.getCanonicalName()),
    TYPE_TIMESTAMP(518, "timestamp", Long.class.getCanonicalName()),
    TYPE_OBJECT(1024, "object", Object.class.getCanonicalName());

    private int code;
    private String msg;
    private String javaTypeDesc;

    private IndicatorValueTypeEnum(int code, String msg, String javaTypeDesc) {
        this.code = code;
        this.msg = msg;
        this.javaTypeDesc = javaTypeDesc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJavaTypeDesc() {
        return this.javaTypeDesc;
    }

    public void setJavaTypeDesc(String javaTypeDesc) {
        this.javaTypeDesc = javaTypeDesc;
    }

    public static IndicatorValueTypeEnum valueOf(int code) {
        IndicatorValueTypeEnum[] typeEna = values();
        IndicatorValueTypeEnum[] var2 = typeEna;
        int var3 = typeEna.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            IndicatorValueTypeEnum typeEnum = var2[var4];
            if (typeEnum != null && typeEnum.getCode() == code) {
                return typeEnum;
            }
        }

        return null;
    }
}
