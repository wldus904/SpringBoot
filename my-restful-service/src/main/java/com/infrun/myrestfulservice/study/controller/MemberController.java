package com.infrun.myrestfulservice.study.controller;

import com.infrun.myrestfulservice.exception.UserDuplicateException;
import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.dto.MemberDto;
import com.infrun.myrestfulservice.study.dto.MemberLoginDto;
import com.infrun.myrestfulservice.study.dto.TokenDto;
import com.infrun.myrestfulservice.study.entity.Member;
import com.infrun.myrestfulservice.study.service.MemberJoinService;
import com.infrun.myrestfulservice.study.service.MemberLoginService;
import com.infrun.myrestfulservice.study.service.MemberService;
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
    public CommonResponse createMember(@RequestBody MemberDto memberDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            memberJoinService.joinMember(memberDto);
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
        List<Member> members = memberService.getAllMember();
        return new CommonResponse(members);
    }

    @GetMapping("/{id}")
    public CommonResponse getMemberById(@PathVariable String id) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            Member member = memberService.getMemberById(id);
            commonResponse.setData(member);
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
