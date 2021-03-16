package com.changyi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo1")
public class DemoController {

    @RequestMapping("/t1")
    public String home() {
        return "demo1";
    }

}
