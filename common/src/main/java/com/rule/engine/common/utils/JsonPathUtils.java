package com.rule.engine.common.utils;

import com.alibaba.fastjson.JSONPath;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/9 11:43 上午
 * description：
 */
public class JsonPathUtils {

    /**
     * 解析json value
     *
     * @param jsonVal
     * @param path
     * @return
     */
    public static Object read(String jsonVal, String path) {
        return JSONPath.read(jsonVal, path);
    }
}
