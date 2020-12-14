package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("取消预约请求")
public class ReserveCancelReq {
    @NotBlank(message = "内容ID不能为空")
    @ApiModelProperty("内容ID")
    private Integer contentId;

    @NotBlank(message = "预约ID不能为空")
    @ApiModelProperty("预约ID")
    private Integer reserveId;

    @NotBlank(message = "openId不能为空")
    @ApiModelProperty("openId")
    private String openId;

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public String getOpenId() {
        return openId;
    }
}
