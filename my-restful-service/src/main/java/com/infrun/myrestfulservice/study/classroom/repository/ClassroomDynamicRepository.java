package com.infrun.myrestfulservice.study.classroom.repository;

import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.classroom.dto.ClassroomCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import static com.infrun.myrestfulservice.study.board.entity.QBoard.board;
import static com.infrun.myrestfulservice.study.board.entity.QBoardConfig.boardConfig;
import static com.infrun.myrestfulservice.study.classroom.entiry.QClassroom.classroom;
import static com.infrun.myrestfulservice.study.classroom.entiry.QClassStaff.classStaff;


@Repository
@RequiredArgsConstructor
public class ClassroomDynamicRepository {
    private JPAQueryFactory jpaQueryFactory;

//    public Page<Classroom> findAll(ClassroomCondition condition) {
//
//    }


//    private JPAQuery<Board> fetchSelect(BoardCondition condition, Integer boardConfigId) {
//        return jpaQueryFactory
//                .selectFrom(board)
//                .join(board.boardConfig, boardConfig)
//                .where(
//                        containTitle(condition),
//                        eqBoardConfigId(boardConfigId)
//                ).orderBy(board.regDate.desc());
//    }

    public BooleanExpression eqYear(ClassroomCondition condition) {
        return classroom.year.eq(condition.getYear());
    }

    public BooleanExpression eqGrade(ClassroomCondition condition) {
        return classroom.grade.eq(condition.getGrad());
    }

    public BooleanExpression eqClassSection(ClassroomCondition condition) {
        return classroom.classSection.eq(condition.getClassSection());
    }

    public BooleanExpression eqHomeroomTeacher(ClassroomCondition condition) {
        return classStaff.member.memberId.eq(condition.getSubjectTeacher());
    }
}
