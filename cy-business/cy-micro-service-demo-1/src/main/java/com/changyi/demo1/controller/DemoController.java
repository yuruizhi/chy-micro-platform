package com.changyi.demo1.controller;

import com.changyi.demo1.entity.Test1Entity;
import com.changyi.demo1.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(tags = "Demo1模块")
@RestController
@RequestMapping("/test1")
public class DemoController {

    @Autowired
    private TestService testService;


    @ApiOperation(value = "Demo1 api")
    @GetMapping("/t1")
    public String home() {
        return "demo1";
    }


    @GetMapping("/t2")
    public Object t2() {

        log.debug("demo11111111111111");
        log.error("123312313123");

        testService.test1();
        log.error("123312313123");
        Test1Entity result = new Test1Entity();
        result.setA(111);
        result.setB("nnb");
        result.setC(223L);
        return result;
    }


    @ApiOperation(value = "Demo1 api t3")
    @RequestMapping(value = "/t3",method = RequestMethod.POST)
    public Test1Entity t3(@RequestBody Test1Entity t) {
        t.setA(t.getA()+1);
        return t;
    }

    @ApiOperation(value = "Demo1 api t4")
    @PostMapping("/t4")
    public Test1Entity t4(@RequestBody Test1Entity t) {
        t.setA(t.getA()+1);
        return t;
    }
}
