package com.changyi.fegin.service;

import com.changyi.common.dispose.Result;
import com.changyi.fegin.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @descriptions: fegin测试调用示例
 * @author: wuwh
 * @date: 2021/3/30 16:37
 * @version: 1.0
 */

//feign:sentinel:enabled: true   fallback 要生效需要开启此参数 且该Demo2ServiceFeginFallBack.class 接口默认值是在访问负载、重试机制完成后才会触发
@FeignClient(value = "demo2", fallback = Demo2ServiceFeginFallBack.class)
public interface Demo2ServiceFegin {

    /**
     * 功能描述: get 参数示例
     */
    @RequestMapping(value = "/getPort", method = RequestMethod.GET)
    String getPort(@RequestParam("roleCodes") String code);


    /**
     * 功能描述: get 多参数示例，返回实体示例
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    Result<User> getUser(@RequestParam("name") String name, @RequestParam("pw") String pw);

    /**
     * 功能描述: POST 参数示例，返回实体示例
     */
    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    Result<User> PostUser(@RequestBody User user);
}
