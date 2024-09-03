package com.infrun.myrestfulservice.study.exam.service;

import com.infrun.myrestfulservice.study.exam.dto.ExamDto;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreDto;
import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import com.infrun.myrestfulservice.study.exam.repository.ExamRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamScoreRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamSubjectRepository;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamScoreService {
    private final ExamRepository examRepository;
    private final ExamSubjectRepository examSubjectRepository;
    private final MemberRepository memberRepository;
    private final ExamScoreRepository examScoreRepository;

    @Transactional
    public ExamScoreDto saveExamScore(ExamScoreDto examScoreDto) {
        ExamSubject examSubject = examSubjectRepository
                .findById(examScoreDto.getExamSubjectDto().getExamSubjectId())
                .orElseThrow(() -> new BadCredentialsException("잘못된 과목 정보입니다."));

        Member member = memberRepository
                .findById(examScoreDto.getMemberDto().getMemberId())
                .orElseThrow(() -> new BadCredentialsException("잘못된 학생 정보입니다."));

        ExamScore examScore = ExamScore.builder()
                .exam(examSubject.getExam())
                .examSubject(examSubject)
                .member(member)
                .score(examScoreDto.getScore())
                .build();

        return ExamScoreDto.toDto(examScoreRepository.save(examScore));
    }
}
