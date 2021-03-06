package com.sheng.thur.practice.mapper;

import com.sheng.thur.practice.domain.entity.Member;
import com.sheng.thur.practice.exception.DaoException;
import org.apache.ibatis.annotations.Param;

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
    List<Member> selectList(@Param("index") int index, @Param("rows") int rows) throws DaoException;

    /**
     * 根据传入的条件进行模糊查询
     *
     * @param condition 用户名、邮箱或者手机号码
     * @param startTime 查找时间区间开始
     * @param endTime   查找时间区间结束
     * @param index     开始查询的索引
     * @param rows      查询的挑食
     * @return 查询到的 member 集合
     * @throws DaoException dao 层可能发生的异常
     */
    List<Member> selectByCondition(@Param("condition") String condition,
                                   @Param("startTime") String startTime,
                                   @Param("endTime") String endTime,
                                   @Param("index") int index,
                                   @Param("rows") int rows) throws DaoException;

    /**
     * 根据主键查询会员
     *
     * @param memberId 会员 id，主键
     * @return Member 对象
     */
    Member selectStatusByPrimaryKey(@Param("memberId") int memberId);

    /**
     * 根据传入的 Member 对象进行修改
     *
     * @param member Member 对象
     * @return 影响行数
     */
    int update(@Param("member") Member member);

    /**
     * 根据传入的 member 对象进行插入
     *
     * @param member member 对象
     * @return 影响行数
     */
    int insert(@Param("member") Member member);

    /**
     * 根据主键进行假删除，设置为 -1
     *
     * @param memberId 会员 id
     * @return 影响行数
     */
    int deleteMember(@Param("memberId") int memberId);
}
