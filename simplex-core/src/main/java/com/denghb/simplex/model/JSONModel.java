package com.denghb.simplex.model;

public class JSONModel<T> {

    private int code = 1;

    private String msg;

    private T result;

    public static JSONModel<String> buildSuccess(String msg) {
        JSONModel<String> json = new JSONModel<>();
        json.setMsg(msg);
        json.setResult("");
        return json;
    }

    public static <T> JSONModel buildSuccess(T t) {
        JSONModel<T> json = new JSONModel<>();
        json.setMsg("ok");
        json.setResult(t);
        return json;
    }

    public static JSONModel<String> buildFailure(String msg) {
        JSONModel<String> json = new JSONModel<>();
        json.setCode(0);
        json.setMsg(msg);
        json.setResult("");
        return json;
    }

    public static JSONModel<String> buildFailure(int code, String msg) {
        JSONModel<String> json = new JSONModel<>();
        json.setCode(code);
        json.setMsg(msg);
        json.setResult("");
        return json;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JSONModel{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
