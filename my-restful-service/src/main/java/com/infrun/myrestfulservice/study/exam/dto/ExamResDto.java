package com.infrun.myrestfulservice.study.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExamResDto {
    private final Integer examId;
    private final Integer examSubjectId;
    private final Integer examScoreId;
    private final int semester;
    private final String title;
    private final String subjectCode;
    private final String subjectName;
    private final int score;
    private final String memberId;
    private final String memberName;
}
