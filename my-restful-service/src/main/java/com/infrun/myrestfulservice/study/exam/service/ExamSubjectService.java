package com.infrun.myrestfulservice.study.exam.service;

import com.infrun.myrestfulservice.study.exam.dto.ExamSubjectDto;
import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import com.infrun.myrestfulservice.study.exam.repository.ExamRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamSubjectRepository;
import com.infrun.myrestfulservice.study.subject.entity.Subject;
import com.infrun.myrestfulservice.study.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamSubjectService {
    private final ExamSubjectRepository examSubjectRepository;
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public ExamSubjectDto saveExamSubject(ExamSubjectDto examSubjectDto) {
        Exam exam = examRepository
                .findById(examSubjectDto.getExamDto().getExamId())
                .orElseThrow(() -> new BadCredentialsException("잘못된 시험 정보입니다."));

        Subject subject = subjectRepository
                .findById(examSubjectDto.getSubject().getSubjectCode())
                .orElseThrow(() -> new BadCredentialsException("잘못된 과목 코드입니다."));

        ExamSubject examSubject = ExamSubject.builder()
                .exam(exam)
                .subject(subject)
                .tester(examSubjectDto.getTester())
                .examDate(examSubjectDto.getExamDate())
                .examOrder(examSubjectDto.getExamOrder())
                .examDurationMinute(examSubjectDto.getExamDurationMinute())
                .grade(examSubjectDto.getGrade())
                .build();

        return ExamSubjectDto.toDto(examSubjectRepository.save(examSubject));
    }

    @Transactional
    public void deleteExamSubject(Integer examSubjectId) {
        examSubjectRepository.deleteById(examSubjectId);
    }
}
