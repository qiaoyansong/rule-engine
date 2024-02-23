package com.rule.engine.biz.beanmapper;

import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;
import com.rule.engine.biz.bo.EventBO;
import com.rule.engine.dal.domain.EventBaseInfoDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 6:00 下午
 * description：
 */
@Mapper(componentModel = "spring")
public interface EventBeanMapper {

    EventBO eventBaseInfoDO2EventBO(EventBaseInfoDO eventBaseInfoDO);

    List<EventBO> eventBaseInfoDO2EventBO(List<EventBaseInfoDO> eventBaseInfoDO);

    EventBaseInfoDO misAddEventInfoParam2EventBaseInfoDO(MisAddEventInfoParam param);
}
