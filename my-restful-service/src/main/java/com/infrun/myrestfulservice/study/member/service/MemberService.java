package com.infrun.myrestfulservice.study.member.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean existByPhone (String phone) {
        return memberRepository.existsByPhone(phone);
    }
    public boolean existById (String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    public MemberDto getMember(String id, String name) {
        if (!id.isEmpty() && !name.isEmpty()) {
            return getMemberByMemberIdAndName(id, name);
        } else if (!id.isEmpty()) {
            return getMemberById(id);
        } else {
            return getMemberByName(name);
        }
    }

    public List<MemberDto> getAllMember() {
        return memberRepository.findAll().stream().map(MemberDto::toDto).collect(Collectors.toList());
    }

    public MemberDto getMemberById(String id) {
        return MemberDto.toDto(memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다.")));
    }

    public MemberDto getMemberByName(String name) {
        return MemberDto.toDto(memberRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다.")));
    }

    public MemberDto getMemberByMemberIdAndName(String id, String name) {
        return MemberDto.toDto(memberRepository.findByMemberIdAndName(id, name)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다.")));
    }

    public void deleteMember(String memberId) {
        memberRepository.deleteById(memberId);
    }
}
