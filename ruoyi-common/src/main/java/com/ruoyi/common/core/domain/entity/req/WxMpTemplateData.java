package com.ruoyi.common.core.domain.entity.req;

public class WxMpTemplateData {
    private String name;
    private String value;
    private String color;

    public WxMpTemplateData(String name, String value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }
}
