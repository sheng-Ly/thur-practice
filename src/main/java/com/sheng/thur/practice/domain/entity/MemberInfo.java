package com.sheng.thur.practice.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：会员信息表，对应 member_info
 *
 * @author sheng
 * @date 2020/7/10 19:19
 * @since JDK 1.8
 */
@Data
public class MemberInfo implements Serializable {
    /**
     * 会员信息表id
     */
    private Integer memberInfoId;
    /**
     * 会员 id，外键
     */
    private Integer memberId;
    /**
     * 会员真实姓名
     */
    private String memberInfoRealName;
    /**
     * 会员固定电话
     */
    private String memberInfoTelephone;
    /**
     * 会员性别，默认 -1，0 为女，1 为男
     */
    private Integer memberInfoSex;
    /**
     * 会员地址
     */
    private String memberInfoAddress;
}
