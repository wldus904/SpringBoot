package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GradeClassSectionDto {
    private Integer classroomId;
    private int year;
    private int grade;
    private int classSection;

    public static GradeClassSectionDto toDto(Classroom classroom) {
        GradeClassSectionDto classroomDto = new GradeClassSectionDto();
        classroomDto.setClassroomId(classroom.getClassroomId());
        classroomDto.setYear(classroom.getYear());
        classroomDto.setGrade(classroom.getGrade());
        classroomDto.setClassSection(classroom.getClassSection());

        return classroomDto;
    }
}
