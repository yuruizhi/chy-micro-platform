package com.changyi.demo1.service;

import com.changyi.common.dispose.Result;
import com.changyi.common.core.model.PageResult;
import com.changyi.common.core.service.ISuperService;
import com.changyi.demo1.entity.SysUser;
import com.changyi.demo1.model.UserDTO;
import com.changyi.demo1.model.UserVO;

import java.util.Map;

/**
 * @author YuRuizhi
 * @date 2021.3.30
 */
public interface ISysUserService extends ISuperService<SysUser> {

    /**
     * 新增或更新用户
     *
     * @param userDTO
     * @return Result
     * @throws Exception
     */
    Result saveOrUpdateUser(UserDTO userDTO) throws Exception;

    /**
     * 查询用户.
     * @param id
     * @return UserVO
     * @throws Exception
     */
    UserVO findUserById(String id);

    /**
     * 用户分页列表.
     * @param params
     * @return PageResult
     */
    PageResult<UserVO> findUsers(Map<String, Object> params);

    /**
     * 删除用户.
     * @param id
     * @return boolean
     */
    boolean delUser(String id);
}
