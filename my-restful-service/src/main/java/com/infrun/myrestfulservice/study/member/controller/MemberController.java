package com.infrun.myrestfulservice.study.member.controller;

import com.infrun.myrestfulservice.exception.UserDuplicateException;
import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import com.infrun.myrestfulservice.study.member.dto.MemberJoinDto;
import com.infrun.myrestfulservice.study.member.dto.MemberLoginDto;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.service.MemberJoinService;
import com.infrun.myrestfulservice.study.member.service.MemberLoginService;
import com.infrun.myrestfulservice.study.member.service.MemberService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberJoinService memberJoinService;
    @Autowired
    private MemberLoginService memberLoginService;

    @PostMapping("/join")
    public CommonResponse createMember(@RequestBody MemberJoinDto memberJoinDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            memberJoinService.joinMember(memberJoinDto);
        } catch (UserDuplicateException e) {
            commonResponse.setError(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return commonResponse;
    }

    @PostMapping("/login")
    public CommonResponse loginMember(@RequestBody MemberLoginDto memberLoginDto, HttpServletResponse response) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            memberLoginService.login(memberLoginDto, response);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @PostMapping("/reissue")
    public CommonResponse reissue(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
        CommonResponse commonResponse = new CommonResponse();

        try {
            memberLoginService.reissue(request, response);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping
    public CommonResponse getAllMember() {
        List<MemberDto> memberDtos = memberService.getAllMember();
        return new CommonResponse(memberDtos);
    }

    @GetMapping("/{id}")
    public CommonResponse getMemberById(@PathVariable String id) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            MemberDto memberDto = memberService.getMemberById(id);
            commonResponse.setData(memberDto);
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/{memberId}")
    public CommonResponse deleteMember(@PathVariable String memberId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            memberService.deleteMember(memberId);
            commonResponse.setStatus(HttpStatus.NO_CONTENT.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
