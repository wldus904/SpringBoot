package com.infrun.myrestfulservice.study.exam.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberDto {
    private String memberId;
    private String name;
    private int totalScore;
    private double avgScore;
    private List<ExamDetailsDto> exams;
}
