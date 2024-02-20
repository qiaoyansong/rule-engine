package com.rule.engine.biz.beanmapper;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.dal.domain.RemoteMethodInfo;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:35 下午
 * description：
 */
@Mapper(componentModel = "spring")
public interface RemoteMethodCalculationBeanMapper {

    @Mapping(source = "argumentList", target = "argInfo", qualifiedByName = "list2String")
    RemoteMethodInfo remoteMethodCalculationParam2RemoteMethodInfo(RemoteMethodCalculationParam param);

    @Named("list2String")
    default String list2String(List<RemoteMethodCalculationParam.ArgInfo> argInfoList) {
        if (CollectionUtils.isEmpty(argInfoList)) {
            return null;
        }
        return JSON.toJSONString(argInfoList);
    }

}
