package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.ClassStudent;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Data;

@Data
public class ClassStudentDto {
    private MemberDto memberDto;

    public static ClassStudentDto toDto (ClassStudent classStudent) {
        ClassStudentDto classStudentDto = new ClassStudentDto();
        classStudentDto.setMemberDto(MemberDto.toDto(classStudent.getMember()));

        return classStudentDto;
    }
}
