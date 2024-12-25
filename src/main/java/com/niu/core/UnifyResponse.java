package com.niu.core;

public class UnifyResponse {
    private int code;
    private String message;
    private String request;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UnifyResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", request='" + request + '\'' +
                '}';
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }
}
