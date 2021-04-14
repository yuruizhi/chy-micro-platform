package com.changyi.fegin.service;

import com.changyi.common.dispose.Result;
import com.changyi.fegin.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Demo2ServiceFeginFallBack implements Demo2ServiceFegin{
    @Override
    public String getPort(String code) {
        return "获取超时";
    }

    @Override
    public Result<User> getUser(String name, String pw) {
        return null;
    }

    @Override
    public Result<User> PostUser(User user) {
        return null;
    }
}
