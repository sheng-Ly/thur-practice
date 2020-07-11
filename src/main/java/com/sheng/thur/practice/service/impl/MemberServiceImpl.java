package com.sheng.thur.practice.service.impl;

import com.sheng.thur.practice.domain.dto.MemberConditionDto;
import com.sheng.thur.practice.domain.dto.MemberDto;
import com.sheng.thur.practice.domain.dto.ResultDto;
import com.sheng.thur.practice.domain.entity.Member;
import com.sheng.thur.practice.domain.entity.MemberInfo;
import com.sheng.thur.practice.exception.ServiceException;
import com.sheng.thur.practice.mapper.MemberInfoMapper;
import com.sheng.thur.practice.mapper.MemberMapper;
import com.sheng.thur.practice.service.MemberService;
import com.sheng.thur.practice.util.IsAllFilesNullUtils;
import com.sheng.thur.practice.util.StatusCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource
    private MemberInfoMapper memberInfoMapper;

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
    public ResultDto<List<Member>> findMemberByCondition(MemberConditionDto memberConditionDto) throws Exception {
        // 取信息
        String page = memberConditionDto.getPage();
        String size = memberConditionDto.getSize();
        // 2020-07-11的形式传进来，需要拼接
        String createTime = memberConditionDto.getCreateTime();
        // 非空判断
        // 查询时间区间的开始
        String startTime = "";
        // 查询时间区间的结束
        String endTime = "";
        if (createTime != null && !Objects.equals("", createTime)) {
            startTime = createTime + " 00:00:00";
            endTime = createTime + " 23:59:59";
        }
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
        List<Member> members = memberMapper.selectByCondition(memberConditionDto.getCondition(), startTime, endTime, index, rows);
        // 对数据进行非空判断
        if (members != null) {
            // 有数据即为成功，返回信息
            return ResultDto.success(StatusCodeEnum.SUCCESS, members);
        } else {
            return ResultDto.error(StatusCodeEnum.DAO_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultDto<Integer> update(MemberDto memberDto) throws ServiceException {
        // 首先进行复制信息
        Member member = new Member();
        MemberInfo memberInfo = new MemberInfo();
        // 拷贝赋值
        BeanUtils.copyProperties(memberDto, member);
        BeanUtils.copyProperties(memberDto, memberInfo);
        // 修改前对主要属性进行非空判断
        if (memberDto.getMemberId() == null) {
            // 参数错误
            throw new ServiceException(StatusCodeEnum.PARAM_ERROR);
        }
        // 修改前对参数进行非空判断
        // 排除判断的成员变量名
        String exclude = "memberId";
        if (IsAllFilesNullUtils.isNull(memberDto)) {
            // 如果为空，则无法修改
            return ResultDto.error(StatusCodeEnum.PARAM_ERROR);
        }
        // 修改的影响行数
        int updateMemberRows = 0;
        if (!IsAllFilesNullUtils.isNull(member, exclude)) {
            updateMemberRows = memberMapper.update(member);
        }
        // 修改的影响行数
        int updateMemberInfoRows = 0;
        if (!IsAllFilesNullUtils.isNull(memberInfo, exclude)) {
            updateMemberInfoRows = memberInfoMapper.update(memberInfo);
        }
        // 总共修改的行数
        int rows = updateMemberRows + updateMemberInfoRows;
        // 判断是否修改成功
        if (rows > 0) {
            // 返回封装了修改的总行数的对象
            return ResultDto.success(StatusCodeEnum.SUCCESS, rows);
        } else {
            // 返回封装了修改的总行数
            return ResultDto.error(StatusCodeEnum.UPDATE_ERROR);
        }
    }

    @Override
    public ResultDto<Integer> addMember(MemberDto memberDto) throws ServiceException {
        // 首先进行复制信息
        Member member = new Member();
        MemberInfo memberInfo = new MemberInfo();
        // 拷贝赋值
        BeanUtils.copyProperties(memberDto, member);
        BeanUtils.copyProperties(memberDto, memberInfo);
        // 插入前对 not null 属性进行非空判断
        boolean flag = member.getMemberPassword() == null || Objects.equals("", member.getMemberPassword())
                || member.getMemberPhone() == null || Objects.equals("", member.getMemberPhone());
        if (flag) {
            return ResultDto.error(StatusCodeEnum.PARAM_ERROR);
        }
        // 进行插入
        // 插入的影响行数
        int insertMemberRows = 0;
        insertMemberRows = memberMapper.insert(member);
        // 插入会员信息的影响行数
        int insertMemberInfoRows = 0;
        // 判断是否插入成功，返回自动生成的主键
        int memberPrimaryKey;
        if (insertMemberRows > 0) {
            memberPrimaryKey = member.getMemberId();
            // 设置主键
            memberInfo.setMemberId(memberPrimaryKey);
            // 排除判断的成员变量名
            String fieldName = "memberId";
            // 进行非空判断
            if (!IsAllFilesNullUtils.isNull(memberInfo, fieldName)) {
                insertMemberInfoRows = memberInfoMapper.insert(memberInfo);
            }
        }
        // 总共的影响行数
        int rows = insertMemberRows + insertMemberInfoRows;
        // 判断插入是否成功
        if (rows > 0) {
            // 返回封装了修改的总行数的对象
            return ResultDto.success(StatusCodeEnum.SUCCESS, rows);
        } else {
            // 返回封装了修改的总行数
            return ResultDto.error(StatusCodeEnum.INSERT_ERROR);
        }
    }

    @Override
    public ResultDto<Integer> updateMemberStatus(String memberId) throws ServiceException {
        // 进行非空判断
        if (memberId == null || Objects.equals("", memberId)) {
            throw new ServiceException(StatusCodeEnum.PARAM_ERROR);
        }
        // 主键要大于 0
        int id = Integer.parseInt(memberId);
        if (id <= 0) {
            throw new ServiceException(StatusCodeEnum.PARAM_ERROR);
        }
        // 根据 id 查询状态
        Member member = memberMapper.selectStatusByPrimaryKey(id);
        // 非空判断
        if (member == null) {
            // 账号异常，查询失败
            return ResultDto.error(StatusCodeEnum.STATUS_ERROR);
        }
        Integer status = member.getStatus();
        // 判断状态
        if (status == 0) {
            status = 1;
        } else if (status == 1) {
            status = 0;
        } else {
            throw new ServiceException(StatusCodeEnum.UPDATE_ERROR);
        }
        // 设置状态
        member.setStatus(status);
        // 进行修改
        int updateRows = memberMapper.update(member);
        // 判断是否成功
        if (updateRows > 0) {
            return ResultDto.success(StatusCodeEnum.SUCCESS, updateRows);
        } else {
            return ResultDto.error(StatusCodeEnum.UPDATE_ERROR);
        }
    }

    @Override
    public ResultDto<Integer> deleteMember(String memberId) throws ServiceException {
        // 非空判断
        if (memberId == null || Objects.equals("", memberId)) {
            throw new ServiceException(StatusCodeEnum.PARAM_ERROR);
        }
        // 转换
        int id = Integer.parseInt(memberId);
        // 判断是否是正常主键
        if (id <= 0) {
            throw new ServiceException(StatusCodeEnum.PARAM_ERROR);
        }
        // 进行修改
        int deleteMemberRows = memberMapper.deleteMember(id);
        // 判断是否成功
        if (deleteMemberRows > 0) {
            return ResultDto.success(StatusCodeEnum.SUCCESS, deleteMemberRows);
        } else {
            return ResultDto.error(StatusCodeEnum.DELETE_ERROR);
        }
    }
}
