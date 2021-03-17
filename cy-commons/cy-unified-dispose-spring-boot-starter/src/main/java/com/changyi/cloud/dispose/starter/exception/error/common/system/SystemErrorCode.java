package com.changyi.cloud.dispose.starter.exception.error.common.system;

import com.changyi.common.core.base.IErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户错误代码
 *
 * @author ZhangHao
 * @date 2021/03/17
 */
@Getter
@AllArgsConstructor
public enum SystemErrorCode implements IErrorCodeEnum {
    SYSTEM_ERROR_B0001("B0001", "系统执行出错"),
    SYSTEM_ERROR_B0100("B0100", "系统执行超时"),
    SYSTEM_ERROR_B0101("B0101", "系统订单处理超时"),
    SYSTEM_ERROR_B0200("B0200", "系统容灾功能被触发"),
    SYSTEM_ERROR_B0210("B0210", "系统限流"),
    SYSTEM_ERROR_B0220("B0220", "系统功能降级"),
    SYSTEM_ERROR_B0300("B0300", "系统资源异常"),
    SYSTEM_ERROR_B0310("B0310", "系统资源耗尽"),
    SYSTEM_ERROR_B0311("B0311", "系统磁盘空间耗尽"),
    SYSTEM_ERROR_B0312("B0312", "系统内存耗尽"),
    SYSTEM_ERROR_B0313("B0313", "文件句柄耗尽"),
    SYSTEM_ERROR_B0314("B0314", "系统连接池耗尽"),
    SYSTEM_ERROR_B0315("B0315", "系统线程池耗尽"),
    SYSTEM_ERROR_B0320("B0320", "系统资源访问异常"),
    SYSTEM_ERROR_B0321("B0321", "系统读取磁盘文件失败");

    private String code;

    private String message;
}

