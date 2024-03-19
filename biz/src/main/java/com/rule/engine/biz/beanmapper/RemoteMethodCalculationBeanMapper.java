package com.rule.engine.biz.beanmapper;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.api.mis.result.RemoteMethodVO;
import com.rule.engine.biz.indicator.calc.local.helper.LocalFunctionDefinition;
import com.rule.engine.dal.domain.RemoteMethodInfo;
import org.apache.commons.collections.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

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

    @Mapping(target = "remoteMethodName", source = "functionName")
    @Mapping(target = "remoteMethodReturnType", source = "functionValueType")
    @Mapping(target = "remoteMethodDesc", source = "functionDesc")
    @Mapping(target = "argumentList", source = "args", qualifiedByName = "argCfg2ArgInfoVO")
    RemoteMethodVO localFunctionDefinition2RemoteMethodVO(LocalFunctionDefinition localFunctionDefinition);

    @Named("argCfg2ArgInfoVO")
    default List<RemoteMethodVO.ArgInfoVO>  argCfg2ArgInfoVO(List<LocalFunctionDefinition.ArgCfg> argCfgs) {
        if (CollectionUtils.isEmpty(argCfgs)) {
            return null;
        }
        return argCfgs.stream()
                .map(argCfg -> {
                    RemoteMethodVO.ArgInfoVO argInfoVO = new RemoteMethodVO.ArgInfoVO();
                    argInfoVO.setArgDesc(argCfg.getArgDesc());
                    argInfoVO.setArgName(argCfg.getArgName());
                    argInfoVO.setArgType(argCfg.getArgType().getCode());
                    return argInfoVO;
                })
                .collect(Collectors.toList());
    }

    List<RemoteMethodVO> localFunctionDefinition2RemoteMethodVO(List<LocalFunctionDefinition> allDefinition);
}
