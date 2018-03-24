package com.jingluu.admin.auth.controller;

public class ResponseBean<T> {
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILED = "failed";

    private int status;
    private String message;
    private T data;

    public ResponseBean(){
        this.status = 0;
        this.message = STATUS_SUCCESS;
    }

    public void fail(){
        this.status = 1;
        this.message = STATUS_FAILED;
    }

    public void fail(String message){
        this.status = 1;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
