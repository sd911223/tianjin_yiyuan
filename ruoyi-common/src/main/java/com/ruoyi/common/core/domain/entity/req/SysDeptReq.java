package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("部门请求实体")
public class SysDeptReq {
    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Long deptId;
    /**
     * 父部门ID
     */
    @NotNull(message = "父部门ID不能为空")
    @ApiModelProperty("父部门ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /**
     * 显示顺序
     */
    @NotBlank(message = "显示顺序不能为空")
    @ApiModelProperty("显示顺序(整数类型.数字越大展示在后)")
    private String orderNum;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDeptId() {
        return deptId;
    }

    public String getRemark() {
        return remark;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
