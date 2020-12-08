package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("用户请求实体")
public class SysUserReq {
    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    private String userName;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idCard;
    /**
     * 角色组
     */
    @ApiModelProperty("角色组")
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @ApiModelProperty("岗位组")
    private Long[] postIds;

}
