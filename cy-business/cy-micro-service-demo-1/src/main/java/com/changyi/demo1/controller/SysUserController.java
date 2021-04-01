package com.changyi.demo1.controller;


import com.changyi.common.dispose.Result;
import com.changyi.common.core.model.PageResult;
import com.changyi.demo1.model.UserDTO;
import com.changyi.demo1.model.UserVO;
import com.changyi.demo1.service.ISysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户模块API.
 *
 * @author Henry.yu
 * @date 2021.3.30
 */
@Slf4j
@Api(tags = "用户模块api")
@RestController
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    private ISysUserService appUserService;


    @ApiOperation(value = "新增或更新用户")
    @CacheEvict(value = "user", key = "#sysUser.username")
    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody UserDTO userDTO) throws Exception {
        appUserService.saveOrUpdateUser(userDTO);
    }

    @ApiOperation(value = "查询用户")
    @GetMapping(value = "/{id}")
    public UserVO findById(@ApiParam("用户ID") @PathVariable String id) {
        return appUserService.findUserById(id);
    }


    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        appUserService.delUser(id);
    }
    @ApiOperation(value = "用户查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/")
    public PageResult<UserVO> findUsers(@RequestParam Map<String, Object> params) {
        return appUserService.findUsers(params);
    }

}
