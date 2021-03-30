package com.changyi.demo1.controller;


import com.changyi.demo1.model.SysUser;
import com.changyi.demo1.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块API.
 *
 * @author Henry.yu
 * @date 2021.3.30
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {


    @Autowired
    private ISysUserService appUserService;


    /**
     * 通过手机号查询用户信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    public SysUser findByMobile(String mobile) {
        return appUserService.findByMobile(mobile);
    }


}
