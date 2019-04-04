package com.denghb.simplex.base;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JSONModel<T> {

    private int code;

    private String msg;

    private T data;

    private JSONModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static JSONModel<String> buildSuccess(String msg) {

        return new JSONModel<String>(1, msg, null);
    }

    public static <T> JSONModel<T> buildSuccess(String msg, T data) {

        return new JSONModel<T>(1, msg, data);
    }

    public static JSONModel<String> buildFailure(String msg) {

        return new JSONModel<String>(0, msg, null);
    }

    public static JSONModel<String> buildFailure(int code, String msg) {

        return new JSONModel<String>(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}