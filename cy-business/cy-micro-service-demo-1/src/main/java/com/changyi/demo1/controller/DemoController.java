package com.changyi.demo1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Demo1模块")
@RestController
@RequestMapping("/demo1")
public class DemoController {

    @ApiOperation(value = "Demo1 api")
    @GetMapping("/t1")
    public String home() {
        return "demo1";
    }

}
