package com.infrun.myrestfulservice.study.classroom.dto;

import com.infrun.myrestfulservice.study.classroom.entiry.ClassStudent;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClassroomDto {
    private Integer classroomId;
    private int year;
    private int grade;
    private int classSection;
    private String homeroomTeacher;
    private List<ClassStudentDto> classStudents;
    private List<ClassStaffDto> classStaff;

    public static ClassroomDto toDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setClassroomId(classroom.getClassroomId());
        classroomDto.setYear(classroom.getYear());
        classroomDto.setClassSection(classroom.getClassSection());
        classroomDto.setHomeroomTeacher(classroom.getHomeroomTeacher());

        List<ClassStudentDto> studentDtos = classroom.getClassStudent().stream()
                .map(ClassStudentDto::toDto)
                .collect(Collectors.toList());
        classroomDto.setClassStudents(studentDtos);

        List<ClassStaffDto> staffDtos = classroom.getClassStaff().stream()
                .map(ClassStaffDto::toDto)
                .collect(Collectors.toList());
        classroomDto.setClassStudents(studentDtos);

        return classroomDto;
    }
}
