package com.infrun.myrestfulservice.study.classroom.dto;

import lombok.Data;

@Data
public class ClassroomCondition {
    private int year;
    private int grad;
    private int classSection;
    private String homeroomTeacher;
    private String subjectTeacher;
    private int size = 10;
    private int page = 1;
}
