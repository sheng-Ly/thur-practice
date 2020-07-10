package com.sheng.thur.practice.domain.dto;

import com.sheng.thur.practice.util.StatusCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：service 层输出数据的封装
 *
 * @author sheng
 * @date 2020/7/9 21:55
 * @since JDK 1.8
 */
@Data
public class ResultDto<T> implements Serializable {
    private Integer status;
    private String message;
    private String hint;
    private T data;

    public ResultDto() {
    }

    /**
     * 成功返回信息
     *
     * @param statusCodeEnum 携带了状态码信息的对象
     * @param data           数据
     * @param <T>            泛型
     * @return javaBean
     */
    public static <T> ResultDto<T> success(StatusCodeEnum statusCodeEnum, T data) {
        // 返回的结果
        ResultDto<T> resultDto = new ResultDto<>();
        // 设置信息
        resultDto.setStatus(statusCodeEnum.getStatus());
        resultDto.setMessage(statusCodeEnum.getMessage());
        resultDto.setHint(statusCodeEnum.getHint());
        resultDto.setData(data);
        // 返回数据
        return resultDto;
    }

    /**
     * 错误返回的信息
     *
     * @param statusCodeEnum 携带了状态码信息的对象
     * @param <T>            泛型
     * @return 错误信息
     */
    public static <T> ResultDto<T> error(StatusCodeEnum statusCodeEnum) {
        // 返回的结果
        ResultDto<T> resultDto = new ResultDto<>();
        // 设置信息
        resultDto.setStatus(statusCodeEnum.getStatus());
        resultDto.setMessage(statusCodeEnum.getMessage());
        resultDto.setHint(statusCodeEnum.getHint());
        // 返回数据
        return resultDto;
    }
}
