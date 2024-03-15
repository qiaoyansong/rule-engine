package com.rule.engine.biz.indicator.calc.generic;

import com.rule.engine.api.mis.param.generic.RemoteMethodCalculationParam;
import com.rule.engine.biz.bo.CalculationResultBO;
import com.rule.engine.biz.bo.RemoteMethodCalculationInfoBO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/1/29 5:32 下午
 * description：
 */
public interface GenericOptService {

    /**
     * 添加外部服务
     *
     * @param param
     * @return
     */
    Boolean addRemoteMethod(RemoteMethodCalculationParam param);

    /**
     * 外部服务计算。
     *
     * @param calculationInfoBO 外部服务计算参数
     *
     * @return
     */
    CalculationResultBO handleRemoteMethodCalculation(RemoteMethodCalculationInfoBO calculationInfoBO);
}
