package com.infrun.myrestfulservice.study.exam.dto;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamDto {
    private Integer examId;
    private int semester;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String title;

    public static ExamDto toDto(Exam exam) {
        ExamDto examDto = new ExamDto();

        examDto.setExamId(exam.getExamId());
        examDto.setSemester(exam.getSemester());
        examDto.setRegDate(exam.getRegDate());
        examDto.setModDate(exam.getModDate());
        examDto.setTitle(exam.getTitle());

        return examDto;
    }
}
