package com.denghb.simplex.common.base;

public class AuthException extends RuntimeException {

    public final static String DEFAULT_MESSAGE = "服务器忙，请稍后重试";

    private Integer code = 0;

    public AuthException() {
        this(DEFAULT_MESSAGE);
    }
    public AuthException(String message) {
        super(message);
    }

    public AuthException(int code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
