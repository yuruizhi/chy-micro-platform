package com.changyi.log.config;


import com.changyi.log.interceptor.MyRequestBodyAdviceAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@Configuration
@ConditionalOnClass(RequestBodyAdviceAdapter.class)
public class ParamsLogConfig {

    @Bean
    public MyRequestBodyAdviceAdapter myRequestBodyAdviceAdapter(){
        return new MyRequestBodyAdviceAdapter();
    }


}
