package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import static com.infrun.myrestfulservice.study.exam.entity.QExam.exam;

@Repository
@RequiredArgsConstructor
public class ExamDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

}
