package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.ClassStudent;
import com.infrun.myrestfulservice.study.member.entity.Member;
import lombok.Data;

@Data
public class ClassStudentDto {
    private Member member;

    public static ClassStudentDto toDto (ClassStudent classStudent) {
        ClassStudentDto classStudentDto = new ClassStudentDto();
        classStudentDto.setMember(classStudent.getMember());

        return classStudentDto;
    }

//    public static ClassStudentDto toDTO (ClassStudent classStudent) {
//        ClassStudentDto classStudentDto = new ClassStudentDto();
//        classStudentDto.setMemberId(classStudent.getMember().getMemberId());
//        classStudentDto.setClassroomId(classStaff.getClassroom().getClassroomId());
//        classStudentDto.setSubjectCode(classStaff.getSubjectCode());
//        return dto;
//    }
}
