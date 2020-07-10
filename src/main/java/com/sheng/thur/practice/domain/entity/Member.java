package com.sheng.thur.practice.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：会员表
 *
 * @author sheng
 * @date 2020/7/9 20:11
 * @since JDK 1.8
 */
@Data
public class Member implements Serializable {
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 会员用户名，用于登陆
     */
    private String memberName;
    /**
     * 会员密码，不能为空
     */
    private String memberPassword;
    /**
     * 会员手机号，不能为空，用于登录
     */
    private String memberPhone;
    /**
     * 会员邮箱，可为空，可用于登录
     */
    private String memberEmail;
    /**
     * 会员创建时间
     */
    private Date memberCreateTime;
    /**
     * 会员等级，默认为 0，普通用户
     */
    private Integer memberGrade;
    /**
     * 状态，默认为 1 激活状态，0 为停用(用户不可登录)，-1 为假删除状态
     */
    private Integer status;
    /**
     * 会员信息表，一对一关系
     */
    private MemberInfo memberInfo;
}
