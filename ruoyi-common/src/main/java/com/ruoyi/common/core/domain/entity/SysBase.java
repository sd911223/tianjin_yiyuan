package com.ruoyi.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("基础设置")
public class SysBase {
    @ApiModelProperty("初始化密码")
    private String passWord;

    @ApiModelProperty("预约设置")
    private String reserveInstall;

    public String getPassWord() {
        return passWord;
    }


    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getReserveInstall() {
        return reserveInstall;
    }

    public void setReserveInstall(String reserveInstall) {
        this.reserveInstall = reserveInstall;
    }
}
