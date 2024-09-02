package com.infrun.myrestfulservice.study.exam.service;

import com.infrun.myrestfulservice.study.exam.dto.ExamDto;
import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamService {
    private final ExamRepository examRepository;

    @Transactional
    public ExamDto saveExam(ExamDto examDto) {
        Exam exam = Exam.builder()
                .semester(examDto.getSemester())
                .title(examDto.getTitle())
                .build();

        return ExamDto.toDto(examRepository.save(exam));
    }

    @Transactional
    public void deleteExam(Integer examId) {
        examRepository.deleteById(examId);
    }
}
