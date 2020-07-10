package com.sheng.thur.practice.util;

/**
 * Description：定义状态码、成功或者错误信息
 *
 * @author sheng
 * @date 2020/7/9 20:22
 * @since JDK 1.8
 */
public enum StatusCodeEnum {
    /**
     * 成功的返回信息
     */
    SUCCESS(20000, "success", "成功"),
    /**
     * 未知错误相关信息
     */
    ERROR(40000, "error", "失败"),
    /**
     * 数据库的异常信息
     */
    DAO_ERROR(40001, "dao_error", "数据异常，请重新尝试"),
    /**
     * 业务层的异常信息
     */
    SERVICE_ERROR(40002, "service_error", "系统繁忙，请稍后再试");

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 是否成功信息
     */
    private String message;
    /**
     * 对于是否成功信息的解读
     */
    private String hint;

    StatusCodeEnum(Integer status, String message, String hint) {
        this.status = status;
        this.message = message;
        this.hint = hint;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
