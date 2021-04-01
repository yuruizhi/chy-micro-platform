package com.changyi.demo2;

import com.changyi.common.dispose.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalDispose
public class ServiceDemo2 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo2.class, args);
    }

}
