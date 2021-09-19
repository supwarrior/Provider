package com.github.model;

/**
 * 错误分析
 * @author 康盼Java开发工程师
 */
public class FailureAnalysis {

    /**
     * 错误描述
     */
    private final String description;

    /**
     * 后续操作
     */
    private final String action;

    /**
     * 异常信息
     */
    private final Throwable cause;

    public FailureAnalysis(String description, String action, Throwable cause) {
        this.description = description;
        this.action = action;
        this.cause = cause;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAction() {
        return this.action;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
