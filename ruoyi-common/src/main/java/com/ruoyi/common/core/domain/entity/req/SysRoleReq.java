package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("新建角色请求对象")
public class SysRoleReq {
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 角色权限
     */
    @ApiModelProperty("角色权限-功能范围")
    @NotBlank(message = "角色权限不能为空")
    private String roleKey;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }
}
