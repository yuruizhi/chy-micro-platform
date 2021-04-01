package com.changyi.log.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import zipkin2.internal.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.List;

@Slf4j
@ConditionalOnClass(HandlerInterceptorAdapter.class)
public class Slf4jLogInterceptor extends HandlerInterceptorAdapter {

    private static final String REQUEST_START_TIMESTAMP = "request_start_timestamp";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute(REQUEST_START_TIMESTAMP,System.currentTimeMillis());
        log.info("开始请求接口:{}--method:{}-query:{}",request.getRequestURI(),request.getMethod(),request.getQueryString());

        return true;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long time =System.currentTimeMillis() - (long)request.getAttribute(REQUEST_START_TIMESTAMP) ;
        log.info("结束接口:{}--耗时:{}ms",request.getRequestURI(),time);
    }


}
