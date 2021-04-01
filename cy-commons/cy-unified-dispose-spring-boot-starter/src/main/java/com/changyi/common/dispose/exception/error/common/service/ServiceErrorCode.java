package com.changyi.common.dispose.exception.error.common.service;

import com.changyi.common.core.base.IErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ServiceErrorCode implements IErrorCodeEnum {
    SERVICE_ERROR_C0001("C0001", "调用第三方服务出错"),
    SERVICE_ERROR_C0100("C0100", "中间件服务出错"),
    SERVICE_ERROR_C0110("C0110", "RPC服务出错"),
    SERVICE_ERROR_C0111("C0111", "RPC服务未找到"),
    SERVICE_ERROR_C0112("C0112", "RPC服务未注册"),
    SERVICE_ERROR_C0113("C0113", "接口不存在"),
    SERVICE_ERROR_C0120("C0120", "消息服务出错"),
    SERVICE_ERROR_C0121("C0121", "消息投递出错"),
    SERVICE_ERROR_C0122("C0122", "消息消费出错"),
    SERVICE_ERROR_C0123("C0123", "消息订阅出错"),
    SERVICE_ERROR_C0124("C0124", "消息分组未查到"),
    SERVICE_ERROR_C0130("C0130", "缓存服务出错"),
    SERVICE_ERROR_C0131("C0131", "key长度超过限制"),
    SERVICE_ERROR_C0132("C0132", "value长度超过限制"),
    SERVICE_ERROR_C0133("C0133", "存储容量已满"),
    SERVICE_ERROR_C0134("C0134", "不支持的数据格式"),
    SERVICE_ERROR_C0140("C0140", "配置服务出错"),
    SERVICE_ERROR_C0150("C0150", "网络资源服务出错"),
    SERVICE_ERROR_C0151("C0151", "VPN服务出错"),
    SERVICE_ERROR_C0152("C0152", "CDN服务出错"),
    SERVICE_ERROR_C0153("C0153", "域名解析服务出错"),
    SERVICE_ERROR_C0154("C0154", "网关服务出错"),
    SERVICE_ERROR_C0200("C0200", "第三方系统执行超时"),
    SERVICE_ERROR_C0210("C0210", "RPC执行超时"),
    SERVICE_ERROR_C0220("C0220", "消息投递超时"),
    SERVICE_ERROR_C0230("C0230", "缓存服务超时"),
    SERVICE_ERROR_C0240("C0240", "配置服务超时"),
    SERVICE_ERROR_C0250("C0250", "数据库服务超时"),
    SERVICE_ERROR_C0300("C0300", "数据库服务出错"),
    SERVICE_ERROR_C0311("C0311", "表不存在"),
    SERVICE_ERROR_C0312("C0312", "列不存在"),
    SERVICE_ERROR_C0321("C0321", "多表关联中存在多个相同名称的列"),
    SERVICE_ERROR_C0331("C0331", "数据库死锁"),
    SERVICE_ERROR_C0341("C0341", "主键冲突"),
    SERVICE_ERROR_C0400("C0400", "第三方容灾系统被触发"),
    SERVICE_ERROR_C0401("C0401", "第三方系统限流"),
    SERVICE_ERROR_C0402("C0402", "第三方功能降级"),
    SERVICE_ERROR_C0500("C0500", "通知服务出错"),
    SERVICE_ERROR_C0501("C0501", "短信提醒服务失败"),
    SERVICE_ERROR_C0502("C0502", "语音提醒服务失败"),
    SERVICE_ERROR_C0503("C0503", "邮件提醒服务失败");

    private String code;

    private String message;
}
