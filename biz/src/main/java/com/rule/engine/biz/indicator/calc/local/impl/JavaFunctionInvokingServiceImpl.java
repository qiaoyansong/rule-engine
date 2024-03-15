package com.rule.engine.biz.indicator.calc.local.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.biz.bo.CalculationResultBO;
import com.rule.engine.biz.bo.FunctionCalculationInfoBO;
import com.rule.engine.biz.indicator.calc.local.JavaFunctionInvokingService;
import com.rule.engine.biz.indicator.calc.local.helper.LocalFunctionDefinition;
import com.rule.engine.biz.indicator.calc.local.helper.LocalJavaFunctionHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/6 5:01 下午
 * description：
 */
@Service
public class JavaFunctionInvokingServiceImpl implements JavaFunctionInvokingService {

    @Resource
    private LocalJavaFunctionHelper localJavaFunctionHelper;

    @Override
    public CalculationResultBO invokeJavaFunction(FunctionCalculationInfoBO functionCalculationInfoBO) {
        String functionName = functionCalculationInfoBO.getFunctionName();
        if (Strings.isNullOrEmpty(functionName)) {
            return null;
        }
        LocalFunctionDefinition info = localJavaFunctionHelper.getDefinition(functionName);
        if (Objects.isNull(info)) {
            return null;
        }
        List<Object> arguments = transformToJavaFunctionArgs(functionCalculationInfoBO.getArgumentList());
        if (CollectionUtils.isEmpty(arguments) || arguments.size() != info.getArgs().size()) {
            return null;
        }
        Object result = IndicatorValueTypeEnum.parseVal(functionCalculationInfoBO.getFunctionMethodReturnType(), ReflectionUtils.invokeMethod(info.getMethod(), info.getObject(), arguments));
        CalculationResultBO calculationResultBO = new CalculationResultBO();
        calculationResultBO.setDataType(functionCalculationInfoBO.getFunctionMethodReturnType());
        calculationResultBO.setValue(result);
        return calculationResultBO;
    }

    private List<Object> transformToJavaFunctionArgs(List<FunctionCalculationInfoBO.ArgumentBO> argumentBOList) {
        List<Object> arguments = Lists.newArrayList();
        if (CollectionUtils.isEmpty(argumentBOList)) {
            return arguments;
        }
        for (FunctionCalculationInfoBO.ArgumentBO argumentBO : argumentBOList) {
            if (argumentBO == null || argumentBO.getArgValue() == null) {
                continue;
            }
            Object argValue = IndicatorValueTypeEnum.parseVal(argumentBO.getArgType(), argumentBO.getArgValue());
            if (Objects.isNull(argValue)) {
                return null;
            }
            arguments.add(argValue);
        }
        return arguments;
    }
}
