package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.dto.ExamScoreCondition;
import com.infrun.myrestfulservice.study.exam.dto.ExamSelectDto;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.infrun.myrestfulservice.study.exam.entity.QExamScore.examScore;
import static com.infrun.myrestfulservice.study.exam.entity.QExamSubject.examSubject;
import static com.infrun.myrestfulservice.study.exam.entity.QExam.exam;

@Repository
@RequiredArgsConstructor
public class ExamScoreDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 평균 백분율 전교석차 반석차

    public List<ExamSelectDto> findByAll(ExamScoreCondition condition) {
        JPAQuery<ExamSelectDto> selectQuery = fetchSelect(condition);
        List<ExamSelectDto> content = selectQuery.fetch();

        return content;
    }


    private JPAQuery<ExamSelectDto> fetchSelect(ExamScoreCondition condition) {
        return jpaQueryFactory
                .select(Projections.constructor(ExamSelectDto.class,
                        examScore.examId,
                        examScore.examSubjectId,
                        examScore.examScoreId,
                        exam.year,
                        exam.classroom.grade,
                        exam.classroom.classSection,
                        exam.semester,
                        exam.title,
                        examSubject.subject.subjectCode,
                        examSubject.subject.subjectName,
                        examScore.score,
                        examScore.member.memberId,
                        examScore.member.name
                ))
                .from(examScore)
                .leftJoin(exam).on(examScore.examId.eq(exam.examId))
                .leftJoin(examSubject).on(examScore.examSubjectId.eq(examSubject.examSubjectId))
                .where(
                        eqMemberId(condition),
                        eqYear(condition),
                        eqGrade(condition),
                        eqClassSection(condition),
                        eqSubjectCode(condition)
                );
    }

    public BooleanExpression eqMemberId(ExamScoreCondition condition) {
        return condition.getMemberId() != null
                ? examScore.member.memberId.eq(condition.getMemberId())
                : null;
    }

    public BooleanExpression eqYear(ExamScoreCondition condition) {
        return condition.getYear() != 0
                ? exam.year.eq(condition.getYear())
                : null;
    }

    public BooleanExpression eqGrade(ExamScoreCondition condition) {
        return condition.getGrade() != 0
                ? exam.classroom.grade.eq(condition.getGrade())
                : null;
    }

    public BooleanExpression eqClassSection(ExamScoreCondition condition) {
        return condition.getClassSection() != 0
                ? exam.classroom.classSection.eq(condition.getClassSection())
                : null;
    }

    public BooleanExpression eqSubjectCode(ExamScoreCondition condition) {
        return condition.getSubjectCode() != null
                ? examSubject.subject.subjectCode.eq(condition.getSubjectCode())
                : null;
    }
}
