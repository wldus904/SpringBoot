package com.infrun.myrestfulservice.study.classroom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.classroom.entiry.ClassStaff;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Data;

@Data
public class ClassStaffDto {
    @JsonIgnore
    private MemberDto memberDto;
    private String subjectName;
    private String memberId;
    private String name;
    private String address;
    private String email;
    private String phone;

    public static ClassStaffDto toDto (ClassStaff classStaff) {
        ClassStaffDto classStaffDto = new ClassStaffDto();
        classStaffDto.setMemberDto(MemberDto.toDto(classStaff.getMember()));
        classStaffDto.setSubjectName(classStaff.getSubject().getSubjectName());
        classStaffDto.setMemberId(classStaffDto.memberDto.getMemberId());
        classStaffDto.setName(classStaffDto.memberDto.getName());
        classStaffDto.setAddress(classStaffDto.memberDto.getAddress());
        classStaffDto.setEmail(classStaffDto.memberDto.getEmail());
        classStaffDto.setPhone(classStaffDto.memberDto.getPhone());

        return classStaffDto;
    }
}
