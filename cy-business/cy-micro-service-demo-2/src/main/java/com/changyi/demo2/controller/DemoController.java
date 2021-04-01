package com.changyi.demo2.controller;

import com.changyi.demo2.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Api(tags = "Demo2模块")
@RestController
@RequestMapping("/test2")
@Slf4j
public class DemoController {

    @ApiOperation(value = "Demo2 api")
    @RequestMapping("t2")
    public String home() {
        return "demo2,t1";
    }

    @GetMapping(value = "cy")
	public String hello() {
		return "nihao";
	}

	@GetMapping(value = "cy/one")
	public String one() {
		 throw new RuntimeException("我错了");
	}

	@PostMapping(value = "cy/two")
	public String two(@Validated @RequestBody User user) {
		log.info("入参：", user);
		return "two";
	}

	@GetMapping(value = "cy/three")
	public String getBookInfo(@NotBlank(message = "书籍ID不能为空") String bookId) {
		return "three";
	}


	@GetMapping(value = "cy/four")
	public void four() {
		System.out.println("four");
	}

}
