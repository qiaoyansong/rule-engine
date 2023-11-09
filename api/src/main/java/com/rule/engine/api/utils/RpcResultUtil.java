package com.rule.engine.api.utils;

import com.rule.engine.api.constant.RpcCode;
import com.rule.engine.api.enums.ErrorCodeEnum;
import com.rule.engine.api.result.RpcResult;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:28 下午
 * description：Rpc结果包装工具类
 */
public class RpcResultUtil {

    public static <T> RpcResult<T> create(T data, String msg) {
        RpcResult<T> rpcResult = new RpcResult<>();
        rpcResult.setCode(RpcCode.SUCCESS);
        rpcResult.setMsg(msg);
        rpcResult.setData(data);
        return rpcResult;
    }

    public static <T> RpcResult<T> success(T data) {
        RpcResult<T> result = new RpcResult<>();
        result.setData(data);
        result.setMsg("SUCCESS");
        result.setCode(RpcCode.SUCCESS);
        return result;
    }

    public static <T> RpcResult<T> fail(String msg) {
        return fail(null, RpcCode.SYSTEM_ERROR, msg);
    }

    public static <T> RpcResult<T> fail() {
        return fail(null, RpcCode.SYSTEM_ERROR, ErrorCodeEnum.SYSTEM_ERROR.getDesc());
    }

    public static <T> RpcResult<T> fail(ErrorCodeEnum errorCodeEnum) {
        return fail(errorCodeEnum.getCode(), errorCodeEnum.getDesc());
    }

    public static <T> RpcResult<T> fail(Integer code, String message){
        return fail(null, code, message);
    }

    public static <T> RpcResult<T> fail(T data, Integer code, String message){
        RpcResult result = new RpcResult();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

}
