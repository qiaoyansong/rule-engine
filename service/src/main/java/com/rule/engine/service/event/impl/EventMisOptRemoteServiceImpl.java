package com.rule.engine.service.event.impl;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.enums.EventSourceEnum;
import com.rule.engine.api.mis.event.EventMisOptRemoteService;
import com.rule.engine.api.mis.generic.GenericMisOptRemoteService;
import com.rule.engine.api.mis.param.event.MisAddEventInfoParam;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.utils.RpcResultUtil;
import com.rule.engine.biz.event.EventOptService;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.util.BizChecker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/2/22 4:23 下午
 * description：
 */
@Service
public class EventMisOptRemoteServiceImpl implements EventMisOptRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericMisOptRemoteService.class);

    @Resource
    private EventOptService eventOptService;

    @Override
    public RpcResult<Boolean> addEventInfo(MisAddEventInfoParam param) {
        try {
            BizChecker.checkMisCommonOpt(param, ErrorCodeEnum.PARAM_ERROR, null);
            BizChecker.check(Objects.nonNull(EventSourceEnum.getByCode(param.getEventSource())), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(param.getEventIdentifier()), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(param.getEventName()), ErrorCodeEnum.PARAM_ERROR);
            BizChecker.check(StringUtils.isNotBlank(param.getEventTemplate()), ErrorCodeEnum.PARAM_ERROR);
            return RpcResultUtil.success(eventOptService.addEventInfo(param));
        } catch (BizException e) {
            LOGGER.warn("EventMisOptRemoteServiceImpl#addEventInfo warn param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.warn("EventMisOptRemoteServiceImpl#addEventInfo error param={}, e=", JSON.toJSONString(param), e);
            return RpcResultUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
