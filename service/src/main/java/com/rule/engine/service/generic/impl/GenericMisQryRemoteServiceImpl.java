package com.rule.engine.service.generic.impl;

import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.mis.generic.GenericMisQryRemoteService;
import com.rule.engine.api.mis.result.RemoteMethodVO;
import com.rule.engine.api.result.RpcResult;
import com.rule.engine.api.utils.RpcResultUtil;
import com.rule.engine.biz.beanmapper.RemoteMethodCalculationBeanMapper;
import com.rule.engine.biz.exception.BizException;
import com.rule.engine.biz.indicator.calc.local.helper.LocalJavaFunctionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/18 4:56 下午
 * description：
 */
@Service
public class GenericMisQryRemoteServiceImpl implements GenericMisQryRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericMisQryRemoteService.class);

    @Resource
    private LocalJavaFunctionHelper localJavaFunctionHelper;

    @Resource
    private RemoteMethodCalculationBeanMapper remoteMethodCalculationBeanMapper;

    @Override
    public RpcResult<List<RemoteMethodVO>> listAllLocalJavaFunction() {
        try {
            return RpcResultUtil.success(remoteMethodCalculationBeanMapper.localFunctionDefinition2RemoteMethodVO(localJavaFunctionHelper.getAllDefinition()));
        } catch (BizException e) {
            LOGGER.warn("GenericMisQryRemoteServiceImpl#listAllLocalJavaFunction warn, e=", e);
            return RpcResultUtil.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.warn("GenericMisQryRemoteServiceImpl#listAllLocalJavaFunction error, e=", e);
            return RpcResultUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

}
