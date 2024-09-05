package com.infrun.myrestfulservice.study.exam.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamScoreDto {
    private Integer examSubjectId;
    private Integer examId;
    private ExamSubjectDto examSubject;
    private ExamDto exam;
    private MemberDto member;
    private String subjectCode;
    private int score;
    @JsonIgnore
    private LocalDateTime regDate;
    @JsonIgnore
    private LocalDateTime modDate;

    public static ExamScoreDto toDto (ExamScore examScore) {
        ExamScoreDto examScoreDto = new ExamScoreDto();

        examScoreDto.setExamSubjectId(examScore.getExamSubjectId());
        examScoreDto.setExamId(examScore.getExamId());
        examScoreDto.setMember(MemberDto.toDto(examScore.getMember()));
        examScoreDto.setScore(examScore.getScore());
        examScoreDto.setRegDate(examScore.getRegDate());
        examScoreDto.setModDate(examScore.getModDate());

        return examScoreDto;
    }
}
