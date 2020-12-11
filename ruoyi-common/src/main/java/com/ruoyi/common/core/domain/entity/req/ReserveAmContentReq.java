package com.ruoyi.common.core.domain.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("预约公共内容")
public class ReserveAmContentReq {
    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;
    @ApiModelProperty("日期范围")
    private String appointmentPeriod;
    @ApiModelProperty("内容")
    private String appointmentContent;
    @ApiModelProperty("人数限制")
    private Integer numberLimit;
    @ApiModelProperty("排序字段")
    private Integer sortNumber;

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
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

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }
}
