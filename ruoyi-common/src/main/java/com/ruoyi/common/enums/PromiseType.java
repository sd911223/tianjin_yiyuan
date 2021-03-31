package com.ruoyi.common.enums;

public enum PromiseType {
    ALL("0", "所有"), SPECIFY("1", "指定");
    private final String code;
    private final String info;

    PromiseType(String code, String info) {
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
