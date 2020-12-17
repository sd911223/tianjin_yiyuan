package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("重置密码")
public class ResetPasswordReq {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户密码")
    private String password;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
