package com.sheng.thur.practice.exception;

import com.sheng.thur.practice.util.StatusCodeEnum;

/**
 * Description：
 *
 * @author sheng
 * @date 2020/7/9 20:58
 * @since JDK 1.8
 */
public class ServiceException extends Exception {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误信息提示
     */
    private String hint;

    /**
     * @param statusCode 枚举类型，用于为异常的信息赋值
     */
    public ServiceException(StatusCodeEnum statusCode) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
        this.hint = statusCode.getHint();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
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
