package com.infrun.myrestfulservice.study.classroom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.classroom.entiry.ClassStudent;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Builder;
import lombok.Data;

@Data
public class ClassStudentDto {
    @JsonIgnore
    private MemberDto memberDto;
    private String memberId;
    private String name;
    private String address;
    private String email;
    private String phone;

    public static ClassStudentDto toDto (ClassStudent classStudent) {
        ClassStudentDto classStudentDto = new ClassStudentDto();
        classStudentDto.setMemberDto(MemberDto.toDto(classStudent.getMember()));
        classStudentDto.setMemberId(classStudentDto.memberDto.getMemberId());
        classStudentDto.setName(classStudentDto.memberDto.getName());
        classStudentDto.setAddress(classStudentDto.memberDto.getAddress());
        classStudentDto.setEmail(classStudentDto.memberDto.getEmail());
        classStudentDto.setPhone(classStudentDto.memberDto.getPhone());

        return classStudentDto;
    }
}
