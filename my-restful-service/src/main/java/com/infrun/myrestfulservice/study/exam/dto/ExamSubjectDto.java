package com.infrun.myrestfulservice.study.exam.dto;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import com.infrun.myrestfulservice.study.subject.dto.SubjectDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamSubjectDto {
    private Integer examSubjectId;
    private ExamDto examDto;
    private SubjectDto subject;
    private String tester;
    private LocalDate examDate;
    private int examOrder;
    private int examDurationMinute; // 분단위
    private int grade;

    public static ExamSubjectDto toDto(ExamSubject examSubject) {
        ExamSubjectDto examSubjectDto = new ExamSubjectDto();
        examSubjectDto.setExamSubjectId(examSubject.getExamSubjectId());
        examSubjectDto.setExamDto(ExamDto.toDto(examSubject.getExam()));
        examSubjectDto.setSubject(SubjectDto.toDto(examSubject.getSubject()));
        examSubjectDto.setTester(examSubject.getTester());
        examSubjectDto.setExamDate(examSubject.getExamDate());
        examSubjectDto.setExamOrder(examSubject.getExamOrder());
        examSubjectDto.setExamDurationMinute(examSubject.getExamDurationMinute());
        examSubjectDto.setGrade(examSubject.getGrade());

        return examSubjectDto;
    }
}
