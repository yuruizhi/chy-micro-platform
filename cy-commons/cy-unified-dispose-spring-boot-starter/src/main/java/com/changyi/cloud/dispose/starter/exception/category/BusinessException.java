package com.changyi.cloud.dispose.starter.exception.category;


import com.changyi.cloud.dispose.starter.exception.error.details.BusinessErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link RuntimeException} 通用业务异常
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private String code;
    private boolean isShowMsg = true;

    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
}
