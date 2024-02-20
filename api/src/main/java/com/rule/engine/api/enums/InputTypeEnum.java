package com.rule.engine.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 3:18 下午
 * description：
 */
@Getter
@AllArgsConstructor
public enum InputTypeEnum {

    INPUT(1, "输入框组件"),
    DROPDOWN_RADIO_SELECTION(2, "下拉单选组件"),
    DROPDOWN_MULTIPLE_SELECTION(3, "下拉多选组件"),
    ;
    private Integer code;
    private String desc;
}
