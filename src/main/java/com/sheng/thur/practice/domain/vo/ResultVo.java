package com.sheng.thur.practice.domain.vo;

import com.sheng.thur.practice.util.StatusCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：对于渲染到 UI 的数据的封装
 *
 * @author sheng
 * @date 2020/7/9 21:03
 * @since JDK 1.8
 */
@Data
public class ResultVo<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 是否成功的信息
     */
    private String message;
    /**
     * 是否成功信息的解读
     */
    private String hint;
    /**
     * 携带的数据
     */
    private T data;

    public ResultVo() {
    }

    /**
     * 成功返回信息
     *
     * @param statusCodeEnum 携带了状态码信息的对象
     * @param data           数据
     * @param <T>            泛型
     * @return 返回的 JavaBean
     */
    public static <T> ResultVo<T> success(StatusCodeEnum statusCodeEnum, T data) {
        // 创建返回的对象
        ResultVo<T> resultVo = new ResultVo<>();
        // 设置数据
        resultVo.setStatus(statusCodeEnum.getStatus());
        resultVo.setMessage(statusCodeEnum.getMessage());
        resultVo.setHint(statusCodeEnum.getHint());
        resultVo.setData(data);
        // 返回对象
        return resultVo;
    }

    /**
     * 错误返回的信息
     *
     * @param statusCodeEnum 携带了状态码信息的对象
     * @param <T>            泛型
     * @return 错误信息
     */
    public static <T> ResultVo<T> error(StatusCodeEnum statusCodeEnum) {
        // 创建返回的对象
        ResultVo<T> resultVo = new ResultVo<>();
        // 设置数据
        resultVo.setStatus(statusCodeEnum.getStatus());
        resultVo.setMessage(statusCodeEnum.getMessage());
        resultVo.setHint(statusCodeEnum.getHint());
        // 返回对象
        return resultVo;
    }
}
