package com.changyi.fegin.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "demo1-center")
public interface Demo1ServiceFegin {


    @RequestMapping("/redis/1")
    String getPort();

    @RequestMapping("/redis/2/{roleCodes}")
    String getRedisCache(@PathVariable(value="roleCodes")  String roleCodes);
}
