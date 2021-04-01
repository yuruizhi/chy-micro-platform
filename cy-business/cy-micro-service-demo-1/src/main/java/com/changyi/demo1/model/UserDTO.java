package com.changyi.demo1.model;

import com.changyi.common.core.model.SuperEntity;
import lombok.Data;

/**
 * 用户DTO
 */
@Data
public class UserDTO extends SuperEntity {

    private static final long serialVersionUID = -5886012896705137070L;

    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String mobile;
    private Integer sex;
    private Boolean enabled;
    private String type;
    private String openId;

}
