package com.ruoyi.common.core.domain.entity.resp;

public class WechatResp {
    private String access_token;
    private Integer expires_in;
    private String errcode;
    private String errmsg;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public String getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
