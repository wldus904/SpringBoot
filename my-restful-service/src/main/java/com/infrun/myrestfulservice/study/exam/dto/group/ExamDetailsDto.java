package com.infrun.myrestfulservice.study.exam.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExamDetailsDto {
    private Integer examId;
    private Integer examSubjectId;
    private Integer examScoreId;
    private int semester;
    private String subjectCode;
    private String subjectName;
    private int score;
}
