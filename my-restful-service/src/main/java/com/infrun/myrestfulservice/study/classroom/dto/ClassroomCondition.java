package com.infrun.myrestfulservice.study.classroom.dto;

import lombok.Data;

@Data
public class ClassroomCondition {
    private int year;
    private int grade;
    private int classSection;
    private String studentName;
    private String homeroomTeacherName;
    private String subjectTeacherName;
    private String subjectCode;
    private int size = 10;
    private int page = 1;
}
