package com.github.model;

/**
 * @author 康盼Java开发工程师
 */
public class ResultInfo<T> {

    private String message;

    private String state;

    private T data;

    public ResultInfo(String message, String state) {
        this.message = message;
        this.state = state;
    }

    public ResultInfo(String message, String state, T data) {
        this.message = message;
        this.state = state;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultInfo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
