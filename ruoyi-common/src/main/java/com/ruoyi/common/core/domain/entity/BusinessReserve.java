package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel("预约管理")
public class BusinessReserve {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 预约名称
     */
    @ApiModelProperty("预约名称")
    @NotBlank(message = "预约名称不能为空")
    private String reserveName;
    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Integer deptId;
    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;
    /**
     * 公告开始时间
     */
    @ApiModelProperty("公告开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date announcementStartTime;
    /**
     * 公告结束时间
     */
    @ApiModelProperty("公告结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date announcementEndTime;
    /**
     * 预约开始时间
     */
    @ApiModelProperty("预约开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveStartTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty("预约结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveEndTime;
    /**
     * 撤回时间
     */
    @ApiModelProperty("撤回时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date withdrawTime;
    /**
     * 预约类型 0:所有人;1:特定人群
     */
    @ApiModelProperty("预约类型 0:所有人;1:特定人群")
    private String reserveType;
    /**
     * 预约内容
     */
    @ApiModelProperty("预约内容")
    @NotBlank(message = "预约内容不能为空")
    private String reserveContent;
    /**
     * 预约公共内容
     */
    @ApiModelProperty("预约公共内容")
    @NotBlank(message = "预约公共内容不能为空")
    private String reserveAmContent;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;
    /**
     * 预约登记信息
     */
    @ApiModelProperty("预约登记信息")
    private String reserveRegister;
    /**
     * 预约人数
     */
    @ApiModelProperty("预约人数")
    private Integer reserveNum;
    /**
     * 附件地址
     */
    @ApiModelProperty("附件地址")
    private String annexUrl;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 发布时间
     */
    private Date release;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setAnnouncementStartTime(Date announcementStartTime) {
        this.announcementStartTime = announcementStartTime;
    }

    public void setAnnouncementEndTime(Date announcementEndTime) {
        this.announcementEndTime = announcementEndTime;
    }

    public void setReserveStartTime(Date reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public void setReserveEndTime(Date reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public void setWithdrawTime(Date withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public void setReserveType(String reserveType) {
        this.reserveType = reserveType;
    }

    public void setReserveContent(String reserveContent) {
        this.reserveContent = reserveContent;
    }

    public void setReserveAmContent(String reserveAmContent) {
        this.reserveAmContent = reserveAmContent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReserveRegister(String reserveRegister) {
        this.reserveRegister = reserveRegister;
    }

    public void setReserveNum(Integer reserveNum) {
        this.reserveNum = reserveNum;
    }

    public void setAnnexUrl(String annexUrl) {
        this.annexUrl = annexUrl;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getId() {
        return id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public Date getAnnouncementStartTime() {
        return announcementStartTime;
    }

    public Date getAnnouncementEndTime() {
        return announcementEndTime;
    }

    public Date getReserveStartTime() {
        return reserveStartTime;
    }

    public Date getReserveEndTime() {
        return reserveEndTime;
    }

    public Date getWithdrawTime() {
        return withdrawTime;
    }

    public String getReserveType() {
        return reserveType;
    }

    public String getReserveContent() {
        return reserveContent;
    }

    public String getReserveAmContent() {
        return reserveAmContent;
    }

    public String getStatus() {
        return status;
    }

    public String getReserveRegister() {
        return reserveRegister;
    }

    public Integer getReserveNum() {
        return reserveNum;
    }

    public String getAnnexUrl() {
        return annexUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getRemark() {
        return remark;
    }

    public Date getRelease() {
        return release;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return "BusinessReserve{" +
                "id=" + id +
                ", reserveName='" + reserveName + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", announcementStartTime=" + announcementStartTime +
                ", announcementEndTime=" + announcementEndTime +
                ", reserveStartTime=" + reserveStartTime +
                ", reserveEndTime=" + reserveEndTime +
                ", withdrawTime=" + withdrawTime +
                ", reserveType='" + reserveType + '\'' +
                ", reserveContent='" + reserveContent + '\'' +
                ", reserveAmContent='" + reserveAmContent + '\'' +
                ", status='" + status + '\'' +
                ", reserveRegister='" + reserveRegister + '\'' +
                ", reserveNum=" + reserveNum +
                ", annexUrl='" + annexUrl + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", release=" + release +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }

}
