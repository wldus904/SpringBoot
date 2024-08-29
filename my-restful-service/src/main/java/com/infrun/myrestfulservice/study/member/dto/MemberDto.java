package com.infrun.myrestfulservice.study.member.dto;

import com.infrun.myrestfulservice.study.classroom.dto.ClassStaffDto;
import com.infrun.myrestfulservice.study.classroom.entiry.ClassStaff;
import com.infrun.myrestfulservice.study.member.entity.Member;
import lombok.Data;

@Data
public class MemberDto {
    private String memberId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int grade;

    public static MemberDto toDto (Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(member.getMemberId());
        memberDto.setName(member.getName());
        memberDto.setAddress(member.getAddress());
        memberDto.setEmail(member.getEmail());
        memberDto.setPhone(member.getPhone());
        memberDto.setGrade(member.getGrade());

        return memberDto;
    }
}
