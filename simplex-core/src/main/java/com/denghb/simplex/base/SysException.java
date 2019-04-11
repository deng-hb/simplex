package com.denghb.simplex.base;

public class SysException extends RuntimeException {

    public final static String DEFAULT_MESSAGE = "服务器忙，请稍后重试";

    private Integer code = 0;

    public SysException() {
        this(DEFAULT_MESSAGE);
    }
    public SysException(String message) {
        super(message);
    }

    public SysException(int code, String message) {
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
