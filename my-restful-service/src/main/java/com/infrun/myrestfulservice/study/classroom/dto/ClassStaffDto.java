package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.ClassStaff;
import com.infrun.myrestfulservice.study.classroom.entiry.ClassStudent;
import com.infrun.myrestfulservice.study.member.entity.Member;
import lombok.Data;

@Data
public class ClassStaffDto {
    private Member member;
    private String subjectCode;

    public static ClassStaffDto toDto (ClassStaff classStaff) {
        ClassStaffDto classStaffDto = new ClassStaffDto();
        classStaffDto.setMember(classStaff.getMember());
        classStaffDto.setSubjectCode(classStaff.getSubjectCode());

        return classStaffDto;
    }
}
