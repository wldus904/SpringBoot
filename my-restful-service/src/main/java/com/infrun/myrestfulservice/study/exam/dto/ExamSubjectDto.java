package com.infrun.myrestfulservice.study.exam.dto;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import com.infrun.myrestfulservice.study.subject.dto.SubjectDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamSubjectDto {
    private Integer examSubjectId;
    private Integer examId;
    private SubjectDto subject;

    public static ExamSubjectDto toDto(ExamSubject examSubject) {
        ExamSubjectDto examSubjectDto = new ExamSubjectDto();
        examSubjectDto.setExamSubjectId(examSubject.getExamSubjectId());
        examSubjectDto.setExamId(examSubject.getExamId());
        examSubjectDto.setSubject(SubjectDto.toDto(examSubject.getSubject()));

        return examSubjectDto;
    }
}
