package com.changyi.demo1;

import com.changyi.cloud.dispose.starter.annotation.EnableGlobalDispose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalDispose
public class  ServiceDemo1 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo1.class, args);
        log.info("Demo1工程已启动！");
    }

}
