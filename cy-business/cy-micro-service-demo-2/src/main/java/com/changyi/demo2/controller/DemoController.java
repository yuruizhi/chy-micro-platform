package com.changyi.demo2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Demo2模块")
@RestController
@RequestMapping("/demo2")
public class DemoController {

    @ApiOperation(value = "Demo2 api")
    @RequestMapping("/t2")
    public String home() {
        return "demo2,t1";
    }

}
