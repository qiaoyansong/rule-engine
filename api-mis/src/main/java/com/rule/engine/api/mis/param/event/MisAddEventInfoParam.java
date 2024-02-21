package com.rule.engine.api.mis.param.event;

import com.rule.engine.api.mis.param.BaseMisOptParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/21 11:37 上午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MisAddEventInfoParam extends BaseMisOptParam implements Serializable {

    private static final long serialVersionUID = 332961342689906061L;

    /**
     * 事件类型
     * #{@link com.rule.engine.api.enums.EventSourceEnum}
     */
    private Integer eventSource;

    /**
     * 事件标识
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
     * 事件模板
     */
    private String eventTemplate;

    /**
     * 校验脚本
     */
    private String checkScript;

}
