package com.sheng.thur.practice.mapper;

import com.sheng.thur.practice.domain.entity.Member;
import com.sheng.thur.practice.exception.DaoException;

import java.util.List;

/**
 * Description：对 member 表的 crud 相关操作
 *
 * @author sheng
 * @date 2020/7/9 21:22
 * @since JDK 1.8
 */
public interface MemberMapper {
    /**
     * 根据传入的索引以及条数进行分页查询
     *
     * @param index 开始查询的索引
     * @param rows  查询的挑食
     * @return 查询到的 member 集合
     * @throws DaoException dao 层可能发生的异常
     */
    List<Member> selectList(int index, int rows) throws DaoException;
}
