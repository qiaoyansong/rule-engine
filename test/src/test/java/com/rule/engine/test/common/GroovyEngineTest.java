package com.rule.engine.test.common;

import com.google.common.collect.Maps;
import com.rule.engine.common.utils.GroovyEngine;
import com.rule.engine.test.BaseTestApplication;
import org.junit.Test;

import javax.script.ScriptException;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 7:11 下午
 * description：
 */
public class GroovyEngineTest extends BaseTestApplication {

    @Test
    public void evalReturnBool_Test_Simple() throws ScriptException {
        String script = "ctx['aa'] != null";
        Map<String, Object> param = Maps.newHashMap();
        param.put("aa", 1L);
        System.out.println(GroovyEngine.evalReturnBool(GroovyEngine.compile(script), param));
        param.remove("aa");
        System.out.println(GroovyEngine.evalReturnBool(GroovyEngine.compile(script), param));
    }

    @Test
    public void eval_Test_Simple() throws ScriptException {
        String script = "ctx['aa'].key1";
        Map<String, Object> param = Maps.newHashMap();
        Map<String, Long> aa = Maps.newHashMap();
        param.put("aa", aa);
        aa.put("key1", 1L);
        System.out.println(GroovyEngine.eval(GroovyEngine.compile(script), param));
    }

}
