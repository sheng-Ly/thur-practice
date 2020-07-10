package com.sheng.thur.practice.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：用于接收 json 数据
 *
 * @author sheng
 * @date 2020/7/10 20:37
 * @since JDK 1.8
 */
@Data
public class MemberConditionDto implements Serializable {
    /**
     * 条件
     */
    private String condition;
    /**
     * 时间，格式化参数
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 当前页
     */
    private String page;
    /**
     * 查询数
     */
    private String size;
}
