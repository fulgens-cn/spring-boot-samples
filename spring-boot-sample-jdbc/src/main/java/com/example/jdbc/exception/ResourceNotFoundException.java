package com.example.jdbc.exception;

import com.example.jdbc.common.ResponseCode;
import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

    private Integer errCode = ResponseCode.NOT_FOUND.getCode();

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Integer errCode, String message) {
        super(message);
        this.errCode = errCode;
    }
}
