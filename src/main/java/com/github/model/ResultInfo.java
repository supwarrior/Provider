package com.github.model;

/**
 * @author 康盼Java开发工程师
 */
public class ResultInfo {

    private String message;

    private String state;

    public ResultInfo(String message, String state) {
        this.message = message;
        this.state = state;
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
