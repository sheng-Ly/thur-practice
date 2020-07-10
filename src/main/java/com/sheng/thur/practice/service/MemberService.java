package com.sheng.thur.practice.service;

import com.sheng.thur.practice.domain.dto.ResultDto;
import com.sheng.thur.practice.domain.entity.Member;

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
}
