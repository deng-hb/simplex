package com.denghb.simplex.common.base;

/**
 * 返回模型
 *
 * @param <T>
 */
public class JSONModel<T> {

    private int code;

    private String msg;

    private T data;

    private JSONModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回
     * @param msg
     * @return
     */
    public static JSONModel<String> buildSuccess(String msg) {

        return new JSONModel<String>(1, msg, null);
    }

    public static <T> JSONModel<T> buildSuccessData(T data) {

        return new JSONModel<T>(1, "ok", data);
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

    @Override
    public String toString() {
        return "JSONModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}