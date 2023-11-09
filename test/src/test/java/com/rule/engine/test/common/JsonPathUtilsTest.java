package com.rule.engine.test.common;

import com.alibaba.fastjson.JSON;
import com.rule.engine.api.enums.IndicatorValueTypeEnum;
import com.rule.engine.common.utils.JsonPathUtils;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Test;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 4:10 下午
 * description：
 */
public class JsonPathUtilsTest extends BaseTestApplication {

    @Test
    public void read_Test_simple() {
        String json = "{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\" : \"doe\",\n" +
                "  \"age\"      : 26,\n" +
                "  \"address\"  : {\n" +
                "    \"streetAddress\": \"naist street\",\n" +
                "    \"city\"         : \"Nara\",\n" +
                "    \"postalCode\"   : \"630-0192\",\n" +
                "    \"position\": {\n" +
                "        \"lat\" : 20,\n" +
                "        \"lng\" : 30\n" +
                "    }\n" +
                "  },\n" +
                "  \"phoneNumbers\": [\n" +
                "    {\n" +
                "      \"type\"  : \"iPhone\",\n" +
                "      \"number\": \"0123-4567-8888\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\"  : \"home\",\n" +
                "      \"number\": \"0123-4567-8910\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        System.out.println(JSON.toJSONString(IndicatorValueTypeEnum.parseVal(IndicatorValueTypeEnum.TYPE_TEXT.getCode(), JsonPathUtils.read(json, "$.firstName"))));
        System.out.println(JSON.toJSONString(IndicatorValueTypeEnum.parseVal(IndicatorValueTypeEnum.TYPE_LIST.getCode(), JsonPathUtils.read(json, "$.phoneNumbers..type"))));
    }

}
