package com.rule.engine.biz.indicator.calc.local;

import com.rule.engine.biz.bo.CalculationResultBO;
import com.rule.engine.biz.bo.FunctionCalculationInfoBO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 4:57 下午
 * description：
 */
public interface JavaFunctionInvokingService {

    /**
     * 执行本地Java函数计算.
     * 函数规则信息（包含实际参数）
     *
     * @return
     */
    CalculationResultBO invokeJavaFunction(FunctionCalculationInfoBO functionCalculationInfoBO);
}
