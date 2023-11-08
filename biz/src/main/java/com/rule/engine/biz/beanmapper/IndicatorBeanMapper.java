package com.rule.engine.biz.beanmapper;

import com.rule.engine.biz.bo.IndicatorBaseInfoBO;
import com.rule.engine.dal.domain.IndicatorBaseInfoDO;
import org.mapstruct.Mapper;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 5:25 下午
 * description：
 */
@Mapper(componentModel = "spring")
public interface IndicatorBeanMapper {
    IndicatorBaseInfoBO indicatorBaseInfoDO2IndicatorBaseInfoBO(IndicatorBaseInfoDO queryIndicatorByIndicatorId);
}
