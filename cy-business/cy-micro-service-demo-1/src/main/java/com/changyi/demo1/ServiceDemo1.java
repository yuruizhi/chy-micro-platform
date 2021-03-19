package com.changyi.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ServiceDemo1 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo1.class, args);
    }

}
