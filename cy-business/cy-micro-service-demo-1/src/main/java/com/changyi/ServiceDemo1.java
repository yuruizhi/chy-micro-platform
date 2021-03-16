package com.changyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ServiceDemo1 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo1.class, args);
    }

    @RequestMapping("/t2")
    public String home() {
        return "demo1,t2";
    }
}
