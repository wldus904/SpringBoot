package com.infrun.myrestfulservice.study.classroom.repository;

import com.infrun.myrestfulservice.study.classroom.dto.ClassroomCondition;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.member.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.infrun.myrestfulservice.study.classroom.entiry.QClassroom.classroom;
import static com.infrun.myrestfulservice.study.classroom.entiry.QClassStaff.classStaff;
import static com.infrun.myrestfulservice.study.classroom.entiry.QClassStudent.classStudent;
import static com.infrun.myrestfulservice.study.member.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class ClassroomDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;
    // alias
    QMember memberHomeroomTeacher = new QMember("memberHomeroomTeacher");
    QMember memberSubjectTeacher = new QMember("memberSubjectTeacher");
    QMember memberStudent = new QMember("memberStudent");

    public Page<Classroom> findAll(ClassroomCondition condition) {
        long count = fetchCount(condition);
        int page = (condition.getPage() - 1) * condition.getSize();
        JPAQuery<Classroom> selectQuery = fetchSelect(condition)
                .limit(condition.getSize())
                .offset(page);
        List<Classroom> content = selectQuery.fetch();
        Pageable pageable = PageRequest.of(page, condition.getSize());

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }

    private JPAQuery<Classroom> fetchSelect(ClassroomCondition condition) {

        return jpaQueryFactory
                .selectFrom(classroom)
                .leftJoin(memberHomeroomTeacher)
                .on(classroom.homeroomTeacher.eq(memberHomeroomTeacher.memberId))
                .leftJoin(classroom.classStaff, classStaff)
                .leftJoin(classStaff.member, memberSubjectTeacher)
                .leftJoin(classroom.classStudent, classStudent)
                .leftJoin(classStudent.member, memberStudent)
                .where(
                        eqYear(condition),
                        eqGrade(condition),
                        eqClassSection(condition),
                        likeStudentName(condition),
                        likeHomeroomTeacher(condition),
                        likeSubjectTeacher(condition),
                        eqSubjectCode(condition)
                ).orderBy(classroom.regDate.desc());
    }

    private long fetchCount(ClassroomCondition condition) {
        JPAQuery<Classroom> fetchSelect = fetchSelect(condition);
        JPAQuery<Long> countQuery = fetchSelect.select(classroom.count());
        Long totalCount = countQuery.fetchOne();
        return totalCount == null
                ? 0
                : totalCount;
    }

    public BooleanExpression eqYear(ClassroomCondition condition) {
        return condition.getYear() != 0
                ? classroom.year.eq(condition.getYear())
                : null;
    }

    public BooleanExpression eqGrade(ClassroomCondition condition) {
        return condition.getGrade() != 0
                ? classroom.grade.eq(condition.getGrade())
                : null;
    }

    public BooleanExpression eqClassSection(ClassroomCondition condition) {
        return condition.getClassSection() != 0
                ? classroom.classSection.eq(condition.getClassSection())
                : null;
    }

    public BooleanExpression likeStudentName(ClassroomCondition condition) {
        return condition.getStudentName() != null
                ? memberStudent.name.likeIgnoreCase("%" + condition.getStudentName() + "%")
                :null;
    }

    public BooleanExpression likeHomeroomTeacher(ClassroomCondition condition) {
        return condition.getHomeroomTeacherName() != null
                ? memberHomeroomTeacher.name.likeIgnoreCase("%" + condition.getHomeroomTeacherName() + "%")
                : null;
    }

    public BooleanExpression likeSubjectTeacher(ClassroomCondition condition) {
        return condition.getSubjectTeacherName() != null
                ? memberSubjectTeacher.name.likeIgnoreCase("%" + condition.getSubjectTeacherName() + "%")
                : null;
    }

    public BooleanExpression eqSubjectCode(ClassroomCondition condition) {
        return condition.getSubjectCode() != null
                ? classStaff.subjectCode.eq(condition.getSubjectCode())
                : null;
    }
}
