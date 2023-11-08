package com.rule.engine.biz.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:24 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBO {

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
}
