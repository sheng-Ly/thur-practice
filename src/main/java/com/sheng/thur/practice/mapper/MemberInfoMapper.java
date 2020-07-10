package com.sheng.thur.practice.mapper;

import com.sheng.thur.practice.domain.entity.MemberInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Description：会员信息表的 crud 相关操作
 *
 * @author sheng
 * @date 2020/7/10 19:27
 * @since JDK 1.8
 */
public interface MemberInfoMapper {
    /**
     * 根据传入的 MemberInfo 对象进行修改
     *
     * @param memberInfo MemberInfo 对象
     * @return 影响行数
     */
    int update(@Param("memberInfo") MemberInfo memberInfo);

    /**
     * 根据传入的数据进行插入
     *
     * @param memberInfo MemberInfo 对象
     * @return 影响行数
     */
    int insert(@Param("memberInfo") MemberInfo memberInfo);
}
