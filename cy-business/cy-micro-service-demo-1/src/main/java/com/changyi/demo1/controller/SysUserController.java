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
 * @author 作者 owen E-mail: 624191343@qq.com
 * 用户
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {

    private static final String ADMIN_CHANGE_MSG = "超级管理员不给予修改";


    @Autowired
    private ISysUserService appUserService;


    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    public SysUser findByMobile(String mobile) {
        return appUserService.findByMobile(mobile);
    }


}
