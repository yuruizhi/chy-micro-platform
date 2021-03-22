package com.changyi.demo1.controller;

import com.changyi.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    Environment environment;

    @Autowired
    private RedisRepository redisRepository;


    @GetMapping("/{roleCodes}")
    public String home(@PathVariable String roleCodes) {
        return environment.getProperty("local.server.port");
    }


    @Cacheable(value = "test2", key ="#roleCodes")
    @GetMapping("/2/{roleCodes}")
    public String home2(@PathVariable String roleCodes) {
        return "demo2";
    }

    @Cacheable(value = "begin", keyGenerator ="DefaultKeyGenerator" )
    @GetMapping("/3/{roleCodes}/{key}")
    public String home3(@PathVariable String roleCodes, @PathVariable String key) {
        return "begin";
    }


    @GetMapping("/5/{roleCodes}/{key}")
    public Map home4(@PathVariable String roleCodes, @PathVariable String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("name","sajkdjf");
        map.put("age", roleCodes);
        redisRepository.setExpire(key, map, 123);
        return map;
    }
}
