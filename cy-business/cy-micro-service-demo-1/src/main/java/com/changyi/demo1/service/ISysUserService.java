package com.changyi.demo1.service;

import com.changyi.cloud.dispose.starter.Result;
import com.changyi.common.core.model.PageResult;
import com.changyi.common.core.service.ISuperService;
import com.changyi.demo1.model.SysUser;

import java.util.Map;

/**
 * @author Henry.yu
 * @date 2021.3.30
 */
public interface ISysUserService extends ISuperService<SysUser> {

	SysUser findByMobile(String username);

    SysUser selectByMobile(String mobile);

    /**
     * 用户列表
     * @param params
     * @return
     */
    PageResult<SysUser> findUsers(Map<String, Object> params);

    Result saveOrUpdateUser(SysUser sysUser) throws Exception;

    /**
     * 删除用户
     */
    boolean delUser(Long id);
}
