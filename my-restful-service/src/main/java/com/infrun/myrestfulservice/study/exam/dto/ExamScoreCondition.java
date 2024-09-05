package com.infrun.myrestfulservice.study.exam.dto;

import lombok.Data;

@Data
public class ExamScoreCondition {
    private String memberId;
    private int year;
    private int grade;
    private int classSection;
    private String subjectCode;
    private String sort;
}
