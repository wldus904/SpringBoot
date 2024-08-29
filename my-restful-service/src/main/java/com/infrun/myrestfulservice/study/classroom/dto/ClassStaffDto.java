package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.ClassStaff;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Data;

@Data
public class ClassStaffDto {
    private MemberDto memberDto;
    private String subjectCode;

    public static ClassStaffDto toDto (ClassStaff classStaff) {
        ClassStaffDto classStaffDto = new ClassStaffDto();
        classStaffDto.setMemberDto(MemberDto.toDto(classStaff.getMember()));
        classStaffDto.setSubjectCode(classStaff.getSubjectCode());

        return classStaffDto;
    }
}
