package com.infrun.myrestfulservice.study.member.service;

import com.infrun.myrestfulservice.exception.UserDuplicateException;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j // 로깅 자동 설정
@Service
@RequiredArgsConstructor
public class MemberJoinService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void joinMember(final MemberDto memberDto) throws UserDuplicateException {
        String phone = memberDto.getPhone();

        if (memberService.existByPhone(phone)) {
            throw new UserDuplicateException("이미 등록된 학생입니다.");
        }

        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        long count = memberRepository.count() + 1;
        String memberId = datePart + String.format("%02d", count);
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        memberDto.setMemberId(memberId);
        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .password(encodedPassword)
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .build();

        memberRepository.save(member);
    }
}
