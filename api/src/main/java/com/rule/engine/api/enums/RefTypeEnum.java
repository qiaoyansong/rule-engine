package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/21 11:02 上午
 * description：
 */
@AllArgsConstructor
@Getter
public enum RefTypeEnum {
    REF_INDICATOR(1, "引用"),
    REF_CONST(2, "常量"),
    ;

    private int code;
    private String msg;

    public static RefTypeEnum valueOf(int code) {
        RefTypeEnum[] refTypeEna = values();
        RefTypeEnum[] var2 = refTypeEna;
        int var3 = refTypeEna.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            RefTypeEnum refTypeEnum = var2[var4];
            if (refTypeEnum == null || refTypeEnum.getCode() == code) {
                return refTypeEnum;
            }
        }

        return null;
    }
}
