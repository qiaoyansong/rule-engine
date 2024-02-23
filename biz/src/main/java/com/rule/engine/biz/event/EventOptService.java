package com.rule.engine.biz.event;

import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/22 4:26 下午
 * description：
 */
public interface EventOptService {

    /**
     * 添加事件
     *
     * @param param
     * @return
     */
    Boolean addEventInfo(MisAddEventInfoParam param);
}
