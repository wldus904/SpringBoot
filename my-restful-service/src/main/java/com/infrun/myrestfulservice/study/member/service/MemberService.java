package com.infrun.myrestfulservice.study.member.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Member getMember(String id, String name) {
        if (!id.isEmpty() && !name.isEmpty()) {
            return getMemberByMemberIdAndName(id, name);
        } else if (!id.isEmpty()) {
            return getMemberById(id);
        } else {
            return getMemberByName(name);
        }
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public Member getMemberByMemberIdAndName(String id, String name) {
        return memberRepository.findByMemberIdAndName(id, name)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public void deleteMember(String memberId) {
        memberRepository.deleteById(memberId);
    }
}
