package com.changyi.controller;

import com.changyi.log.trace.TraceUtil;
import com.changyi.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/demo1")
public class DemoController {

    @Autowired
    private TestService testService;

    @RequestMapping("/t1")
    public String home() {
        return "demo1";
    }


    @RequestMapping("/t2")
    public String t2() {

        log.debug("demo11111111111111");
        log.error("123312313123");


        testService.test1();
        System.out.println(TraceUtil.getTraceId()+"----");
        log.error("123312313123");
        return "demo1";
    }




}
