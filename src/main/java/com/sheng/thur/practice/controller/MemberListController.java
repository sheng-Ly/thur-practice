package com.sheng.thur.practice.controller;

import com.sheng.thur.practice.domain.dto.ResultDto;
import com.sheng.thur.practice.domain.entity.Member;
import com.sheng.thur.practice.domain.vo.ResultVo;
import com.sheng.thur.practice.service.MemberService;
import com.sheng.thur.practice.util.StatusCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description：对应 user_list.html 的相关方法
 *
 * @author sheng
 * @date 2020/7/9 22:10
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/memberList")
public class MemberListController {
    @Resource
    MemberService memberService;

    @GetMapping("/findMemberList")
    public ResultVo<ResultDto<List<Member>>> findMemberList(@RequestParam(defaultValue = "1") String page,
                                                            @RequestParam(defaultValue = "10") String size) throws Exception {
        // 查询数据
        ResultDto<List<Member>> resultDto = memberService.findAllMember(page, size);
        // 进行判断
        if (resultDto != null) {
            // 判断数据是否正常
            if (resultDto.getData() != null) {
                return ResultVo.success(StatusCodeEnum.SUCCESS, resultDto);
            } else {
                return ResultVo.error(StatusCodeEnum.DAO_ERROR);
            }
        } else {
            return ResultVo.error(StatusCodeEnum.SERVICE_ERROR);
        }
    }
}
