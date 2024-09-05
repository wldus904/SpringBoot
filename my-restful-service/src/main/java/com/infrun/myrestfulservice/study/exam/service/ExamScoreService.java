package com.infrun.myrestfulservice.study.exam.service;

import com.infrun.myrestfulservice.study.exam.dto.*;
import com.infrun.myrestfulservice.study.exam.dto.group.*;
import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import com.infrun.myrestfulservice.study.exam.repository.ExamScoreDynamicRepository;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.infrun.myrestfulservice.study.exam.repository.ExamRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamScoreRepository;
import com.infrun.myrestfulservice.study.exam.repository.ExamSubjectRepository;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import com.infrun.myrestfulservice.study.subject.dto.SubjectDto;
import com.infrun.myrestfulservice.study.subject.entity.Subject;
import com.infrun.myrestfulservice.study.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamScoreService {
    private final ExamRepository examRepository;
    private final ExamSubjectRepository examSubjectRepository;
    private final MemberRepository memberRepository;
    private final ExamScoreRepository examScoreRepository;
    private final ExamScoreDynamicRepository examScoreDynamicRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public void saveExamScore(ExamScoreSaveDto examScoreDto) {
//        ExamSubject examSubject = examSubjectRepository
//                .findById(examScoreDto.getExamSubject().getExamSubjectId())
//                .orElseThrow(() -> new BadCredentialsException("잘못된 과목 정보입니다."));

        examScoreDto.getExams().stream().forEach(exam -> {
            Integer subjectId = exam.getExamSubjectId();
            if (exam.getExamSubjectId() == 0) {

                Subject subject = subjectRepository.findById(exam.getSubjectCode())
                        .orElseThrow(() -> new BadCredentialsException("잘못된 과목 정보입니다."));
                ExamSubject saveExamSubject = ExamSubject
                        .builder()
                        .examId(exam.getExamId())
                        .subject(subject)
                        .build();
                ExamSubject examSubject = examSubjectRepository.save(saveExamSubject);
                subjectId = examSubject.getExamSubjectId();
            }

            Member member = memberRepository
                    .findById(examScoreDto.getMemberId())
                    .orElseThrow(() -> new BadCredentialsException("잘못된 학생 정보입니다."));

            ExamScore examScore = ExamScore.builder()
                    .examScoreId(exam.getExamScoreId())
                    .examId(exam.getExamId())
                    .examSubjectId(subjectId)
                    .member(member)
                    .score(exam.getScore())
                    .build();

            ExamScoreDto.toDto(examScoreRepository.save(examScore));
        });
    }

    public List<ExamSelectDto> getExamScore2 (ExamScoreCondition condition) {
        return  examScoreDynamicRepository.findByAll(condition);
    }

    public List<ExamGroupDto> getExamScore (ExamScoreCondition condition) {
        List<ExamSelectDto> results =  examScoreDynamicRepository.findByAll(condition);

        Map<Integer, List<ExamSelectDto>> groupedByExamId = results.stream()
                .collect(Collectors.groupingBy(ExamSelectDto::getExamId, LinkedHashMap::new, Collectors.toList()));

        return groupedByExamId.entrySet().stream()
                .map(entry -> {
                    int examId = entry.getKey();
                    String title = entry.getValue().isEmpty() ? null : entry.getValue().get(0).getTitle();
                    int grade = entry.getValue().isEmpty() ? 0 : entry.getValue().get(0).getGrade();
                    int classSection = entry.getValue().isEmpty() ? 0 : entry.getValue().get(0).getClassSection();
                    int year = entry.getValue().isEmpty() ? 0 : entry.getValue().get(0).getYear();

                    List<ExamDetailsDto> exams = entry.getValue().stream()
                            .map(dto -> new ExamDetailsDto(
                                    dto.getExamId(),
                                    dto.getExamSubjectId(),
                                    dto.getExamScoreId(),
                                    dto.getSemester(),
                                    dto.getSubjectCode(),
                                    dto.getSubjectName(),
                                    dto.getScore()
                            ))
                            .collect(Collectors.toList());

                    // totalScore와 avgScore 계산
                    int totalScore = exams.stream()
                            .mapToInt(ExamDetailsDto::getScore)
                            .sum();
                    double avgScore = exams.stream()
                            .mapToInt(ExamDetailsDto::getScore)
                            .average()
                            .orElse(0);
                    avgScore = Math.round(avgScore * 10) / 10.0;

                    return new ExamGroupDto(examId, year, title, grade, classSection, totalScore, avgScore, exams);
                })
                .collect(Collectors.toList());
    }
}
