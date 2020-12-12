package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("预约人员")
public class BusinessReservePersonnel {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 预约号
     */
    @ApiModelProperty("预约号")
    @Excel(name = "预约号")
    private String reserveNumber;
    /**
     * 日期
     */
    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    /**
     * 日期
     */
    private Date appointmentDate;
    /**
     * 日期范围
     */
    @ApiModelProperty("日期范围")
    private String appointmentPeriod;
    /**
     * 预约名称
     */
    @ApiModelProperty("预约名称")
    private String reserveName;
    /**
     * 预约ID
     */
    @ApiModelProperty("预约ID")
    private Integer reserveId;
    /**
     * 扫码时间
     */
    @ApiModelProperty("扫码时间")
    private Date createTime;
    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Excel(name = "手机号")
    private String phoneNumber;
    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    @Excel(name = "身份证")
    private String idCard;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String education;
    /**
     * 状态 0未办结 1:已办结 2:未到场
     */
    @ApiModelProperty("状态 0未办结 1:已办结 2:未到场")
    @Excel(name = "状态", readConverterExp = "0=未办结,1=已办结,2=未到场")
    private String status;
    /**
     * 签到时间
     */
    @ApiModelProperty("签到时间")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String signTime;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_2;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_3;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_4;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_5;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_6;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_7;
    /**
     * 扩展
     */
    @ApiModelProperty("扩展字段")
    private String expand_8;

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReserveNumber(String reserveNumber) {
        this.reserveNumber = reserveNumber;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentPeriod(String appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setExpand_2(String expand_2) {
        this.expand_2 = expand_2;
    }

    public void setExpand_3(String expand_3) {
        this.expand_3 = expand_3;
    }

    public void setExpand_4(String expand_4) {
        this.expand_4 = expand_4;
    }

    public void setExpand_5(String expand_5) {
        this.expand_5 = expand_5;
    }

    public void setExpand_6(String expand_6) {
        this.expand_6 = expand_6;
    }

    public void setExpand_7(String expand_7) {
        this.expand_7 = expand_7;
    }

    public void setExpand_8(String expand_8) {
        this.expand_8 = expand_8;
    }

    public Integer getId() {
        return id;
    }

    public String getReserveNumber() {
        return reserveNumber;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentPeriod() {
        return appointmentPeriod;
    }

    public String getReserveName() {
        return reserveName;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getSex() {
        return sex;
    }

    public String getEducation() {
        return education;
    }

    public String getStatus() {
        return status;
    }

    public String getExpand_2() {
        return expand_2;
    }

    public String getExpand_3() {
        return expand_3;
    }

    public String getExpand_4() {
        return expand_4;
    }

    public String getExpand_5() {
        return expand_5;
    }

    public String getExpand_6() {
        return expand_6;
    }

    public String getExpand_7() {
        return expand_7;
    }

    public String getExpand_8() {
        return expand_8;
    }
}
