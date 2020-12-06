package com.ruoyi.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 角色 权限表 sys_jurisdiction
 *
 * @author shitou
 */
@ApiModel("角色 权限")
public class SysJurisdiction {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 权限值
     */
    @ApiModelProperty("权限值")
    private String jurisdictionValue;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String jurisdictionName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getId() {
        return id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJurisdictionValue() {
        return jurisdictionValue;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setJurisdictionValue(String jurisdictionValue) {
        this.jurisdictionValue = jurisdictionValue;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysJurisdiction{" +
                "id=" + id +
                ", jurisdictionValue='" + jurisdictionValue + '\'' +
                ", jurisdictionName='" + jurisdictionName + '\'' +
                ", createTime=" + createTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
