package com.sheng.thur.practice.service.impl;

import com.sheng.thur.practice.domain.dto.ResultDto;
import com.sheng.thur.practice.domain.entity.Member;
import com.sheng.thur.practice.mapper.MemberMapper;
import com.sheng.thur.practice.service.MemberService;
import com.sheng.thur.practice.util.StatusCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description：
 *
 * @author sheng
 * @date 2020/7/9 21:51
 * @since JDK 1.8
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;

    @Override
    public ResultDto<List<Member>> findAllMember(String page, String size) throws Exception {
        // 将参数转化数值类型
        // 当前页
        int currentPage = Integer.parseInt(page);
        // 查询数
        int rows = Integer.parseInt(size);
        // 计算索引
        int index = (currentPage - 1) * rows;
        // 查询集合
        List<Member> members = memberMapper.selectList(index, rows);
        // 对数据进行非空判断
        if (members != null) {
            // 有数据即为成功，返回信息
            return ResultDto.success(StatusCodeEnum.SUCCESS, members);
        } else {
            return ResultDto.error(StatusCodeEnum.DAO_ERROR);
        }
    }

    @Override
    public ResultDto<List<Member>> findMemberByCondition(String condition, Date createTime,
                                                         String page, String size) throws Exception {
        // 非空判断
        if (page == null || Objects.equals("", page)) {
            page = "1";
        }
        if (size == null || Objects.equals("", size)) {
            size = "10";
        }
        // 将参数转化数值类型
        // 当前页
        int currentPage = Integer.parseInt(page);
        // 查询数
        int rows = Integer.parseInt(size);
        // 计算索引
        int index = (currentPage - 1) * rows;
        // 查询集合
        List<Member> members = memberMapper.selectByCondition(condition, createTime, index, rows);
        // 对数据进行非空判断
        if (members != null) {
            // 有数据即为成功，返回信息
            return ResultDto.success(StatusCodeEnum.SUCCESS, members);
        } else {
            return ResultDto.error(StatusCodeEnum.DAO_ERROR);
        }
    }

    @Override
    public int update() {
        return 0;
    }
}
