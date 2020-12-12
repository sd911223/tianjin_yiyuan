package com.ruoyi.common.core.domain.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel("现场办理详细查询条件")
public class SiteDetailedReq {
    /**
     * 预约ID
     */
    @ApiModelProperty("预约ID")
    @NotBlank(message = "预约ID不能为空")
    private Integer reserveId;
    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Integer pageNum;
    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize;
    /**
     * 日期
     */
    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;
    /**
     * 日期范围
     */
    @ApiModelProperty("日期范围")
    private String appointmentPeriod;
    /**
     * 预约号
     */
    @ApiModelProperty("预约号")
    private String reserveNumber;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phoneNumber;
    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    private String idCard;

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentPeriod(String appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }

    public void setReserveNumber(String reserveNumber) {
        this.reserveNumber = reserveNumber;
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

    public Integer getReserveId() {
        return reserveId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentPeriod() {
        return appointmentPeriod;
    }

    public String getReserveNumber() {
        return reserveNumber;
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
}
