package com.changyi.demo1.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changyi.common.core.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 * @author zlt
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser extends SuperEntity {

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
    @TableLogic
    private boolean isDel;

}
