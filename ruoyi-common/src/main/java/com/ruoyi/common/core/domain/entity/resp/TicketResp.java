package com.ruoyi.common.core.domain.entity.resp;

public class TicketResp {
    private String ticket;
    private Integer expires_in;
    private Integer errcode;
    private String errmsg;

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
