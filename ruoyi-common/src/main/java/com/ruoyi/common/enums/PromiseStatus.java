package com.ruoyi.common.enums;

public enum PromiseStatus {

    ISSUE("0", "发布"), NO_ISSUE("1", "未发布"), CLOSE_ISSUE("2", "关闭");

    private final String code;
    private final String info;

    PromiseStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
