package com.infrun.myrestfulservice.study.exam.dto;

import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamScoreDto {
    private ExamSubjectDto examSubjectDto;
    private ExamDto examDto;
    private MemberDto memberDto;
    private int score;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static ExamScoreDto toDto (ExamScore examScore) {
        ExamScoreDto examScoreDto = new ExamScoreDto();

        examScoreDto.setExamSubjectDto(ExamSubjectDto.toDto(examScore.getExamSubject()));
        examScoreDto.setExamDto(ExamDto.toDto(examScore.getExam()));
        examScoreDto.setMemberDto(MemberDto.toDto(examScore.getMember()));
        examScoreDto.setScore(examScore.getScore());
        examScoreDto.setRegDate(examScore.getRegDate());
        examScoreDto.setModDate(examScore.getModDate());

        return examScoreDto;
    }
}
