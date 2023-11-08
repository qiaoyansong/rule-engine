package com.rule.engine.api.enums;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 3:26 下午
 * description：
 */
public enum IndicatorTypeEnum {
    INDICATOR_EVENT_PARSING(1, "事件解析"),
    INDICATOR_FUNCTION_INVOKING(2, "函数调用"),
    INDICATOR_RMI_INVOKING(3, "外部服务调用"),
    INDICATOR_ACCUMULATOR_AGGREGATION(4, "累积器聚合");

    private int code;
    private String msg;

    private IndicatorTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public static IndicatorTypeEnum valueOf(int code) {
        IndicatorTypeEnum[] indicatorTypeEna = values();
        IndicatorTypeEnum[] var2 = indicatorTypeEna;
        int var3 = indicatorTypeEna.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            IndicatorTypeEnum indicatorTypeEnum = var2[var4];
            if (indicatorTypeEnum.getCode() == code) {
                return indicatorTypeEnum;
            }
        }

        return null;
    }
}
