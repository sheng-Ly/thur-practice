package com.sheng.thur.practice.util;

import com.sheng.thur.practice.domain.dto.MemberDto;
import com.sheng.thur.practice.exception.ServiceException;
import org.junit.Test;

/**
 * Description：
 *
 * @author sheng
 * @date 2020/7/10 23:57
 * @since JDK 1.8
 */
public class IsAllFieldsNullUtilsTest {
    @Test
    public void isNull() throws ServiceException {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(1);
        memberDto.setMemberInfoId(1);
//        memberDto.setMemberName("1332");
//        memberDto.setMemberEmail("13e434ty");
        System.out.println(IsAllFieldsNullUtils.isNull(memberDto, "memberId", "memberInfoId"));
    }

    @Test
    public void isFieldsNull() throws ServiceException {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(1);
        memberDto.setMemberInfoId(1);
//        memberDto.setMemberName("1332");
//        memberDto.setMemberEmail("13e434ty");
        System.out.println(IsAllFieldsNullUtils.isFieldsNull(memberDto, "memberId", "memberInfoId"));
    }
}
