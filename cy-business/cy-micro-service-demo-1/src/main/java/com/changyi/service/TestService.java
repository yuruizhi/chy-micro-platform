package com.changyi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TestService {

    @Autowired
    TestService1 testService1;

    @Async
    @NewSpan
    public void test1(){
        for (int i = 0; i < 5; i++) {
            log.info("异步{}",i);
        }
        testService1.test2();

    }
}
