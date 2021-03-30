package com.changyi.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.changyi.cloud.dispose.starter.Result;
import com.changyi.common.core.constant.CommonConstant;
import com.changyi.common.core.lock.DistributedLock;
import com.changyi.common.core.model.PageResult;
import com.changyi.common.core.service.impl.SuperServiceImpl;
import com.changyi.demo1.mapper.SysUserMapper;
import com.changyi.demo1.model.SysUser;
import com.changyi.demo1.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final static String LOCK_KEY_USERNAME = "username:";

   /* @Autowired
    private DistributedLock lock;

    @Autowired
    private PasswordEncoder passwordEncoder;*/


    @Override
    public SysUser findByMobile(String username) {
        return this.selectByMobile(username);
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    @Override
    public SysUser selectByMobile(String mobile) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("mobile", mobile)
        );
        return getUser(users);
    }

    private SysUser getUser(List<SysUser> users) {
        SysUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public PageResult<SysUser> findUsers(Map<String, Object> params) {
        Page<SysUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<SysUser> list = baseMapper.findList(page, params);
        long total = page.getTotal();
        return PageResult.<SysUser>builder().data(list).code(0).count(total).build();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveOrUpdateUser(SysUser sysUser) throws Exception {
        if (sysUser.getId() == null) {
            if (StringUtils.isBlank(sysUser.getType())) {
                sysUser.setType("1");
            }
            /*sysUser.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));*/
            sysUser.setPassword("123456");
            sysUser.setEnabled(Boolean.TRUE);
        }
        String username = sysUser.getUsername();
        boolean result = true;/*super.saveOrUpdateIdempotency(sysUser, lock
                , LOCK_KEY_USERNAME + username, new QueryWrapper<SysUser>().eq("username", username)
                , username + "已存在");*/

        return result ? Result.ofSuccess(sysUser) : Result.ofFail("111", "操作失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUser(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

}