package com.rule.engine.service.indicator.impl;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.api.mis.generic.GenericMisOptRemoteService;
import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.utils.RpcResultUtil;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.generic.GenericOptService;
import com.rule.engine.biz.util.BizChecker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:11 下午
 * description：
 */
@Service
public class GenericMisOptRemoteServiceImpl implements GenericMisOptRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericMisOptRemoteService.class);

    @Resource
    private GenericOptService genericOptService;

    @Override
    public RpcResult<Boolean> addRemoteMethod(RemoteMethodCalculationParam param) {
        try {
            BizChecker.check(Objects.nonNull(param), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(param.getRemoteMethodName()), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(Objects.nonNull(IndicatorValueTypeEnum.getByCode(param.getRemoteMethodReturnType())), ErrorCodeEnum.PARAM_ERROR);
            if (CollectionUtils.isNotEmpty(param.getArgumentList())) {
                param.getArgumentList().forEach(argInfo -> {
                    BizChecker.check(StringUtils.isNotBlank(argInfo.getArgName()), ErrorCodeEnum.PARAM_ERROR);
                    BizChecker.check(Objects.nonNull(IndicatorValueTypeEnum.getByCode(argInfo.getArgType())), ErrorCodeEnum.PARAM_ERROR);
                });
            }
            return RpcResultUtil.success(genericOptService.addRemoteMethod(param));
        } catch (BizException e) {
            LOGGER.warn("GenericMisOptRemoteServiceImpl#addRemoteMethod warn param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.warn("GenericMisOptRemoteServiceImpl#addRemoteMethod error param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

}
