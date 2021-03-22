package com.changyi.demo1;

import com.changyi.cloud.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalDispose
public class ServiceDemo1 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo1.class, args);
    }

}
