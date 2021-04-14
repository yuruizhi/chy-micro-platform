package com.changyi.fegin.controller;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.changyi.fegin.service.Demo1ServiceFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeginDemo1Controller {

    @Autowired
    private Demo1ServiceFegin demo1ServiceFegin;

    @GetMapping("/demo1service/getPort")
    public String getDemo1Port(){
        return demo1ServiceFegin.getPort();
    }


    @SentinelResource(value = "demoTest",blockHandler = "exceptionHandler", blockHandlerClass = { ExceptionUtil.class })
    @GetMapping("/demo1service/redis/{roleCodes}")
    public String getReids(@PathVariable String roleCodes){
        return demo1ServiceFegin.getRedisCache(roleCodes);
    }
}
