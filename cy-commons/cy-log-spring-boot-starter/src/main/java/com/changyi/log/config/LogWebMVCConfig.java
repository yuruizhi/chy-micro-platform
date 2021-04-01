package com.changyi.log.config;

import com.changyi.log.interceptor.MyRequestBodyAdviceAdapter;
import com.changyi.log.interceptor.Slf4jLogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Configuration
@ConditionalOnClass(HandlerInterceptorAdapter.class)
public class LogWebMVCConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new Slf4jLogInterceptor());
        interceptorRegistration.addPathPatterns("/**")
                                .excludePathPatterns("/nologtest1/**","/nologtest2/**");
    }

}