package com.infrun.myrestfulservice.study.exam.service;

import com.infrun.myrestfulservice.study.exam.dto.ExamDto;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreCondition;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreDto;
import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.repository.ExamDynamicRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamDynamicRepository examDynamicRepository;

    @Transactional
    public ExamDto saveExam(ExamDto examDto) {
        Exam exam = Exam.builder()
                .semester(examDto.getSemester())
                .title(examDto.getTitle())
                .build();

        return ExamDto.toDto(examRepository.save(exam));
    }

    public List<ExamDto> getExam (ExamScoreCondition condition) {
        return examDynamicRepository.findByAll(condition).stream()
                .map(ExamDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteExam(Integer examId) {
        examRepository.deleteById(examId);
    }
}
