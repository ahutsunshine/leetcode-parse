package com.leetcode.model.response;

public class APIResponse {
    private Integer code;//custom response status,2XX:success,400:request params error,500:server internal error
    private String message;
    private Object data;

    public APIResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public APIResponse(Integer code, String message){
        this(code, message, null);
    }

    public APIResponse(Object data) {
        this(200, null, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
