package com.changyi.common.core.utils;

import com.changyi.common.core.vo.UserVo;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class JsonUtilTest {

    @Test
    void toJSONStr() {
        String[] hobbies = {"football", "sing"};
        UserVo user = new UserVo(1, "iworkh", System.currentTimeMillis(), false, Arrays.asList(hobbies));
        String json = JsonUtil.toJSONStr(user);
        System.out.println(json);
    }

    @Test
    void toBean() {
        String json = "{\"id\":1,\"name\":\"iworkh\",\"birthday\":1592575139578,\"vip\":false,\"hobbies\":[\"football\",\"sing\"]}";
        UserVo userVo = JsonUtil.toBean(json, UserVo.class);
        if (userVo != null) {
            System.out.println(userVo.getName());
        }
    }

    @Test
    void toObj() {
        String json = "{\"id\":1,\"name\":\"iworkh\",\"birthday\":1592575139578,\"vip\":false,\"hobbies\":[\"football\",\"sing\"]}";

        UserVo userVo = JsonUtil.toObj(json,  new TypeReference<UserVo>() {});
        System.out.println(userVo);
    }

    @Test
    void toList() {
        String json = "[{\"id\":1,\"name\":\"iworkh1\",\"birthday\":1592575376189,\"vip\":false,\"hobbies\":[\"football\",\"sing\"]}," + "{\"id\":2,\"name\":\"iworkh2\",\"birthday\":1592575376189,\"vip\":false,\"hobbies\":[\"football\",\"sing\"]}]";
        List<UserVo> userList = JsonUtil.toList(json, UserVo.class);

        userList.forEach(item -> {
            System.out.println(item.getName());
        });
    }

    @Test
    void toMap() {
        String json = "{\"id\":1,\"name\":\"iworkh\",\"birthday\":1592575139578,\"vip\":false,\"hobbies\":[\"football\",\"sing\"]}";

        System.out.println(JsonUtil.toMap(json, String.class, Object.class));
    }
}