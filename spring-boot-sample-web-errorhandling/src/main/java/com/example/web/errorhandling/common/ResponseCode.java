package com.example.web.errorhandling.common;

public enum ResponseCode {

    SUCCESS(200, "success"),
    ERROR(500, "error"),
    UNAUTHORIZED(401, "need login"),
    NOT_FOUND(404, "resource not found");

    private Integer code;

    private String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
