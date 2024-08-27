package com.infrun.myrestfulservice.study.classroom.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassroomDto {
    private Integer classroomId;
    private int year;
    private int grade;
    private int classSection;
    private String homeroomTeacher;
    private LocalDateTime regDate;
}
