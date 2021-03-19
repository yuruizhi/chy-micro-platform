package com.changyi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TestService1 {

    @Async
    @NewSpan
    public void test2(){
        for (int i = 0; i < 5; i++) {
            log.info("异步{}",i);
        }

    }
}
