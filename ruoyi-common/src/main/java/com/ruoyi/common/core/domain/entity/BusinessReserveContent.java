package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("预约内容")
public class BusinessReserveContent {
    /**
     * 主键ID
     */
    private Integer id;
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
     * 内容
     */
    @ApiModelProperty("内容")
    private String appointmentContent;
    /**
     * 人数限制
     */
    @ApiModelProperty("人数限制")
    private Integer numberLimit;
    /**
     * 预约ID
     */
    @ApiModelProperty("预约ID")
    private Integer reserveId;
    /**
     * 剩余人数
     */
    @ApiModelProperty("剩余人数")
    private Integer surplusNumber;
    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private Integer sortNumber;
    private Integer dictionaryId;
    private String submitName;

    @ApiModelProperty("是否在有效期")
    private String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentPeriod(String appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }

    public void setAppointmentContent(String appointmentContent) {
        this.appointmentContent = appointmentContent;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public void setSurplusNumber(Integer surplusNumber) {
        this.surplusNumber = surplusNumber;
    }

    public Integer getId() {
        return id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentPeriod() {
        return appointmentPeriod;
    }

    public String getAppointmentContent() {
        return appointmentContent;
    }

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public Integer getSurplusNumber() {
        return surplusNumber;
    }
}
