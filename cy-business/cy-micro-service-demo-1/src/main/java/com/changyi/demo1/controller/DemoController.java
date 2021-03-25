package com.changyi.demo1.controller;

import com.changyi.log.trace.TraceUtil;
import com.changyi.demo1.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public String t2() {

        log.debug("demo11111111111111");
        log.error("123312313123");

        testService.test1();
        System.out.println(TraceUtil.getTraceId()+"----");
        log.error("123312313123");
        return "demo1";
    }
}
