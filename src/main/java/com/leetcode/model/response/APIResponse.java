package com.leetcode.model.response;

import java.util.Map;

public class APIResponse {
    private Integer code;//自定义返回状态，2XX：成功，400：客户端请求的参数错误，500：服务器内部错误
    private String msg;
    private Object data;

    public APIResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public APIResponse(Integer code, String msg){
        this(code, msg, null);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
