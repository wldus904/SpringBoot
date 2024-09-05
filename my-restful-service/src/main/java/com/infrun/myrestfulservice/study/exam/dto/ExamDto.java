package com.infrun.myrestfulservice.study.exam.dto;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ExamDto {
    private Integer examId;
    private int year;
    private int semester;
//    private List<ExamSubjectDto> examSubject;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String title;

    public static ExamDto toDto(Exam exam) {
        ExamDto examDto = new ExamDto();

        examDto.setExamId(exam.getExamId());
        examDto.setYear(exam.getYear());
        examDto.setSemester(exam.getSemester());
//        examDto.setExamSubject(exam.getExamSubject().stream().map(ExamSubjectDto::toDto).collect(Collectors.toList()));
        examDto.setRegDate(exam.getRegDate());
        examDto.setModDate(exam.getModDate());
        examDto.setTitle(exam.getTitle());

        return examDto;
    }
}
