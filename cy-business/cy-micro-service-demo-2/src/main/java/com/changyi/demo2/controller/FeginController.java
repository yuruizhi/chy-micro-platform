package com.changyi.demo2.controller;

import com.changyi.demo2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

@RestController
@Slf4j
public class FeginController {

    @Autowired
    Environment environment;


    @GetMapping("/getPort")
    public String home(String roleCodes) {
        log.error("fangwen jilu");
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            log.error("yichang",e);
        }

        return "demo2:" + environment.getProperty("local.server.port")+"-roleCodes:" + roleCodes;
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam String name, @RequestParam String pw) {
        User user = new User();
        user.setName(name);
        user.setPassword(pw);
        return user;
    }

    @PostMapping("/postUser")
    public User postUser(@RequestBody User user) {
        log.error(user.toString());
        return user;
    }

}
