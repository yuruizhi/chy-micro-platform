package com.changyi.cloud.dispose.starter;

import com.changyi.common.core.base.IErrorCodeEnum;
import com.changyi.common.core.utils.TraceUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回统一数据结构
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 服务器当前时间戳
     */
    private Long timestamp = System.currentTimeMillis();

    private String traceId = TraceUtil.getTraceId();


    /**
     * 成功数据
     */
    private T data;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

    public static Result ofSuccess() {
        Result result = new Result();
        result.success = true;
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();
        result.success = true;
        result.setData(data);
        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.success = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static Result ofFail(IErrorCodeEnum resultEnum, Object data) {
        Result result = new Result();
        result.success = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        result.setData(data);
        return result;
    }

    public static Result ofFail(IErrorCodeEnum resultEnum) {
        Result result = new Result();
        result.success = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        return result;
    }
}
