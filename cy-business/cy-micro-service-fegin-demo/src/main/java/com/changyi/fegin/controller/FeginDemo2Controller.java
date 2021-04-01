package com.changyi.fegin.controller;


import com.changyi.cloud.dispose.starter.Result;
import com.changyi.common.core.utils.JsonUtil;
import com.changyi.fegin.entity.User;
import com.changyi.fegin.service.Demo1ServiceFegin;
import com.changyi.fegin.service.Demo2ServiceFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

@RestController
@Slf4j
public class FeginDemo2Controller {

    @Autowired
    private Demo2ServiceFegin demo2ServiceFegin;

    @GetMapping("/demo2service/getPort/{code}")
    public String getDemo1Port(@PathVariable String code){
        return demo2ServiceFegin.getPort(code);
    }

    @GetMapping("/demo2service/getUser")
    public User getUser(@RequestParam String name, @RequestParam String pw){
        Result<User> user = demo2ServiceFegin.getUser(name,pw);
        log.info(JsonUtil.toJSONStr(user));
        return user.getData();
    }

    @GetMapping("/demo2service/postUser")
    public User PostUser(@RequestParam String name, @RequestParam String pw){
        User user = new User();
        user.setName(name);
        user.setPassword(pw);

        return demo2ServiceFegin.PostUser(user).getData();
    }
}
