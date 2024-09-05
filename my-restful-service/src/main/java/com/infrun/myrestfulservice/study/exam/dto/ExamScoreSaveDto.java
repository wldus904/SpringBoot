package com.infrun.myrestfulservice.study.exam.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.exam.dto.group.ExamDetailsDto;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.infrun.myrestfulservice.study.member.dto.MemberDto;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamScoreSaveDto {
    private Integer examId;
    private String memberId;
    private List<ExamDetailsDto> exams;
}
