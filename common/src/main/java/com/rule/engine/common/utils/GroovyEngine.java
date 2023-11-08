package com.rule.engine.common.utils;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.*;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/11/8 6:13 下午
 * description：
 */
public class GroovyEngine {

    private static final GroovyScriptEngineImpl ENGINE;

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyEngine.class);


    static {
        ImportCustomizer importCustomizer = new ImportCustomizer();
//        importCustomizer.addImports(GeoUtil.class.getName());
        CompilerConfiguration configuration = new CompilerConfiguration();
        configuration.addCompilationCustomizers(importCustomizer);

        ENGINE = new GroovyScriptEngineImpl(
                AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>) () -> new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), configuration)));
    }

    public static CompiledScript compile(String script) {
        try {
            return ENGINE.compile(script);
        } catch (Exception e) {
            LOGGER.error("compile script error, {}", script, e);
        }
        return null;
    }

    public static Object eval(CompiledScript script) throws ScriptException {
        return eval(script, null);
    }

    public static Boolean evalReturnBool(CompiledScript script) throws ScriptException {
        return evalReturnBool(script, null);
    }

    public static Boolean evalReturnBool(CompiledScript script, Object params) throws ScriptException {
        Object res = eval(script, params);
        if (res instanceof Boolean) {
            return (Boolean) res;
        }
        return Boolean.FALSE;
    }

    public static Object eval(CompiledScript script, Object params) throws ScriptException {
        Bindings bindings = new SimpleBindings();
        bindings.put("ctx", params);
        return eval(script, bindings);
    }

    public static Object eval(CompiledScript script, Bindings bindings) throws ScriptException {
        return script.eval(bindings);
    }

    public synchronized static void globalBindings(String key, Object value) {
        Bindings globalBindings = ENGINE.getContext().getBindings(ScriptContext.GLOBAL_SCOPE);
        if (globalBindings == null) {
            globalBindings = new SimpleBindings();
            ENGINE.getContext().setBindings(globalBindings, ScriptContext.GLOBAL_SCOPE);
        }
        globalBindings.put(key, value);
    }
}
