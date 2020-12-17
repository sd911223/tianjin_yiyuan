package com.ruoyi.common.core.domain.entity.req;

import javax.validation.constraints.NotBlank;

public class TicketReq {
    @NotBlank(message = "url不能为空!")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
