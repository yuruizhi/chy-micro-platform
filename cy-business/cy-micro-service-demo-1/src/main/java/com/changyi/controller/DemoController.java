package com.changyi.controller;

import com.changyi.template.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/demo1")
public class DemoController {

    @Autowired
    private RedisRepository redisRepository;

    @Cacheable(value = "test", key ="#roleCodes")
    @GetMapping("/{roleCodes}")
    public Map home(@PathVariable String roleCodes) {
        Map<String, Object> map = new HashMap<>();
        map.put("name","sajkdjf");
        map.put("age", 1);
        return map;
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
