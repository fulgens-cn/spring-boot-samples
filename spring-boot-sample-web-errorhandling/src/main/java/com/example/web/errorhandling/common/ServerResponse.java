package com.example.web.errorhandling.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // @JSONField(serialize = false) // fastjson
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> success() {
        return build(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), null);
    }

    public static <T> ServerResponse<T> success(String msg) {
        return build(ResponseCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> ServerResponse<T> success(T data) {
        return build(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return build(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> error() {
        return build(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), null);
    }

    public static <T> ServerResponse<T> error(String msg) {
        return build(ResponseCode.ERROR.getCode(), msg, null);
    }

    public static <T> ServerResponse<T> build(Integer code, String msg, T data) {
        return new ServerResponse<>(code, msg, data);
    }

}
