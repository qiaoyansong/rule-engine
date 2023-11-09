package com.rule.engine.service.indicator.impl;

import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.result.indicator.IndicatorResultInfo;
import com.rule.engine.api.utils.RpcResultUtil;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.indicator.calc.IndicatorComputeBizService;
import com.rule.engine.biz.util.BizChecker;
import com.rule.engine.service.indicator.IndicatorComputeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 3:17 下午
 * description：
 */
@Service
public class IndicatorComputeServiceImpl implements IndicatorComputeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorComputeService.class);

    @Resource
    private IndicatorComputeBizService indicatorComputeBizService;

    @Override
    public RpcResult<IndicatorResultInfo> handleBatchIndicatorCalculation(Integer eventSource, String eventName, String body) {
        try {
            BizChecker.check(Objects.nonNull(eventName), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(eventName), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(body), ErrorCodeEnum.PARAM_ERROR);
            return RpcResultUtil.success(indicatorComputeBizService.handleBatchIndicatorCalculation(eventSource, eventName, body));
        } catch (BizException e) {
            LOGGER.warn("IndicatorComputeService#handleBatchIndicatorCalculation warn eventSource={}, eventName={}, body={}, e=", eventSource, eventName, body, e);
            return RpcResultUtil.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.warn("IndicatorComputeService#handleBatchIndicatorCalculation error eventSource={}, eventName:{}, body={}, e=", eventSource, eventName, body, e);
            return RpcResultUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

}
