package com.sheng.thur.practice.service;

import com.sheng.thur.practice.domain.dto.ResultDto;
import com.sheng.thur.practice.domain.entity.Member;

import java.util.Date;
import java.util.List;

/**
 * Description：对于会员的相关业务操作
 *
 * @author sheng
 * @date 2020/7/9 21:47
 * @since JDK 1.8
 */
public interface MemberService {
    /**
     * 根据传入的页数以及记录数分页查询所有的会员，包括停用的账号
     *
     * @param page 当前页数
     * @param size 查询的记录数
     * @return 封装了查询相关结果信息的 javaBean
     * @throws Exception 抛出的异常
     */
    ResultDto<List<Member>> findAllMember(String page, String size) throws Exception;

    /**
     * 根据传入的条件、创建时间、页数以及记录数分页模糊查询所有的会员，包括停用的账号
     *
     * @param condition 用户名、邮箱或者手机号码
     * @param createTime 创建时间
     * @param page 当前页数
     * @param size 查询的记录数
     * @return 封装了查询相关结果信息的 javaBean
     * @throws Exception 抛出的异常
     */
    ResultDto<List<Member>> findMemberByCondition(String condition, Date createTime,
                                                  String page, String size) throws Exception;

    int update();
}
