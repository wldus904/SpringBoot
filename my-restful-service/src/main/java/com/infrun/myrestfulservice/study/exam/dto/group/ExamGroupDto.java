package com.infrun.myrestfulservice.study.exam.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ExamGroupDto {
    private int examId;
    private int year;
    private String title;
    private int grade;
    private int classSection;
    private int totalScore;
    private double avgScore;
    private List<ExamDetailsDto> exams;
}
