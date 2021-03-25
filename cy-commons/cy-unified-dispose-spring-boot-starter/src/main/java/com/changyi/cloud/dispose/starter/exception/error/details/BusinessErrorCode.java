package com.changyi.cloud.dispose.starter.exception.error.details;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务通用异常枚举
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorCode {

    /**
     * 通用业务异常
     */
    BUSINESS_ERROR("D0001", "业务异常");

    private String code;

    private String message;
}
