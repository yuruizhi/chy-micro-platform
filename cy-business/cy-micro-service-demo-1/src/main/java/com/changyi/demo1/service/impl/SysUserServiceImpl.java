package com.changyi.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.changyi.common.core.constant.CommonConstant;
import com.changyi.common.core.lock.DistributedLock;
import com.changyi.common.core.model.PageResult;
import com.changyi.common.core.service.impl.SuperServiceImpl;
import com.changyi.common.dispose.Result;
import com.changyi.demo1.entity.SysUser;
import com.changyi.demo1.mapper.SysUserMapper;
import com.changyi.demo1.model.UserDTO;
import com.changyi.demo1.model.UserVO;
import com.changyi.demo1.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final static String LOCK_KEY_USERNAME = "username:";

    /*@Autowired
    private DistributedLock lock;

    @Autowired
    private PasswordEncoder passwordEncoder;*/


    @Override
    public UserVO findUserById(String id) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("id", id)
        );
        SysUser sysUser = null;
        if (null != users && 0 < users.size()) {
            sysUser = users.get(0);
        }
        if (null != sysUser) {
            return getUserVO(sysUser);
        }
        return null;
    }


    public UserVO getUserVO(SysUser sysUser) {
        if (sysUser != null) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(sysUser, userVO);
            return userVO;
        }
        return null;
    }

    @Override
    public PageResult<UserVO> findUsers(Map<String, Object> params) {
        Page<SysUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));

        long total = page.getTotal();

        List<SysUser> list = baseMapper.findList(page, params);
        List<UserVO> userVOList = new ArrayList<>();
        for (SysUser sysUser : list) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(sysUser, userVO);
            userVOList.add(userVO);
        }
        return PageResult.<UserVO>builder().data(userVOList).code(0).count(total).build();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUser(UserDTO userDTO) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDTO, sysUser);
        if (sysUser.getId() == null) {
            if (StringUtils.isBlank(sysUser.getType())) {
                sysUser.setType("1");
            }
            // sysUser.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            sysUser.setPassword(CommonConstant.DEF_USER_PASSWORD);
            sysUser.setEnabled(Boolean.TRUE);
        }
        String username = sysUser.getUsername();
        /*boolean result = super.saveOrUpdateIdempotency(sysUser, lock
                , LOCK_KEY_USERNAME + username, new QueryWrapper<SysUser>().eq("username", username)
                , username + "已存在");*/
        boolean result = super.saveOrUpdate(sysUser);

        return result ? Result.ofSuccess(sysUser) : Result.ofFail("111", "操作失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUser(String id) {
        return baseMapper.deleteById(id) > 0;
    }

}