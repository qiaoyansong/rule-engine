package com.rule.engine.biz.indicator.calc.local.function;

import com.google.common.collect.ImmutableMap;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionClazz;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionParam;
import com.rule.engine.biz.indicator.calc.local.annotations.LocalFunctionTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2024/3/19 3:28 下午
 * description：
 */
@Component
@LocalFunctionClazz
public class WeatherFunction {

    private ImmutableMap<String, String> transferMap = ImmutableMap.of(
            "clear day", "晴天",
            "cloudy day", "阴天"
    );

    @LocalFunctionTemplate(functionValueType = IndicatorValueTypeEnum.TYPE_TEXT, functionName = "com.rule.engine.biz.indicator.calc.local.function.WeatherFunction.parseWeather", functionDesc = "将天气的英文描述转换成为中文")
    public String parseWeather(@LocalFunctionParam(argName = "weatherEn", argDesc = "天气的英文描述", argType = IndicatorValueTypeEnum.TYPE_TEXT) String weatherEn) {
        if (StringUtils.isBlank(weatherEn)) {
            return weatherEn;
        }
        return transferMap.get(weatherEn);
    }

}
