package com.changyi.common.core.base;


/**
 * 抽象基异常枚举
 *
 * @author zhanghao
 * @date 2021.3.17
 */
public interface IErrorCodeEnum {

    /**
     * 获取异常的状态码
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * 获取异常的提示信息
     *
     * @return {@link String}
     */
    String getMessage();
}