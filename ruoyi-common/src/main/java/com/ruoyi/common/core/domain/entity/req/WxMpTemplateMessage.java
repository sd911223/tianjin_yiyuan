package com.ruoyi.common.core.domain.entity.req;


import java.util.List;

public class WxMpTemplateMessage {
    private String toUser;
    private String templateId;
    private List<WxMpTemplateData> data;

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setData(List<WxMpTemplateData> data) {
        this.data = data;
    }

    public String getToUser() {
        return toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public List<WxMpTemplateData> getData() {
        return data;
    }
}
