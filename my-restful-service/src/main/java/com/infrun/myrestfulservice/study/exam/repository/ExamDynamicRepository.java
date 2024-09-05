package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.dto.ExamScoreCondition;
import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.infrun.myrestfulservice.study.exam.entity.QExam.exam;
import static com.infrun.myrestfulservice.study.exam.entity.QExamScore.examScore;

@Repository
@RequiredArgsConstructor
public class ExamDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Exam> findByAll(ExamScoreCondition condition) {
        JPAQuery<Exam> selectQuery = fetchSelect(condition);
        List<Exam> content = selectQuery.fetch();

        return content;
    }

    private JPAQuery<Exam> fetchSelect(ExamScoreCondition condition) {
        return jpaQueryFactory
                .selectFrom(exam)
                .where(
//                        eqMemberId(condition),
                        eqYear(condition),
                        eqGrade(condition),
                        eqClassSection(condition)
                );
    }

//    public BooleanExpression eqMemberId(ExamScoreCondition condition) {
//        return condition.getMemberId() != null
//                ? examScore.member.memberId.eq(condition.getMemberId())
//                : null;
//    }

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

}
