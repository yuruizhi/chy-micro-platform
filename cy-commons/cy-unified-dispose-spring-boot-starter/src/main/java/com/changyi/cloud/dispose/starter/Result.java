package com.changyi.cloud.dispose.starter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.changyi.common.core.base.IErrorCodeEnum;
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
    private Boolean succ;

    /**
     * 服务器当前时间戳
     */
    private Long ts = System.currentTimeMillis();

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
        result.succ = true;
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();
        result.succ = true;
        result.setData(data);
        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.succ = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static Result ofFail(IErrorCodeEnum resultEnum, Object data) {
        Result result = new Result();
        result.succ = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        result.setData(data);
        return result;
    }

    public static Result ofFail(IErrorCodeEnum resultEnum) {
        Result result = new Result();
        result.succ = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        return result;
    }

    /**
     * 获取 json
     * @return json
     */
    public String buildResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succ", this.succ);
        jsonObject.put("code", this.code);
        jsonObject.put("ts", this.ts);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }
}
