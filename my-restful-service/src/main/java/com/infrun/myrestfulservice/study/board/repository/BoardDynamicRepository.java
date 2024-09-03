package com.infrun.myrestfulservice.study.board.repository;

import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.infrun.myrestfulservice.study.board.entity.QBoard.board;
import static com.infrun.myrestfulservice.study.board.entity.QBoardConfig.boardConfig;

@Repository
@RequiredArgsConstructor
public class BoardDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<Board> findAllByBoardConfigId(BoardCondition condition, Integer boardConfigId) {
        long count = fetchCount(condition, boardConfigId);
        int page = condition.getPage() - 1;
        JPAQuery<Board> selectQuery = fetchSelect(condition, boardConfigId)
                .limit(condition.getSize())
                .offset(page * condition.getSize());
        List<Board> content = selectQuery.fetch();
        Pageable pageable = PageRequest.of(page, condition.getSize());

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }

    private JPAQuery<Board> fetchSelect(BoardCondition condition, Integer boardConfigId) {
        return jpaQueryFactory
                .selectFrom(board)
                .join(board.boardConfig, boardConfig)
                .join(board.member)
                .where(
                    containTitle(condition),
                    eqBoardConfigId(boardConfigId)
                ).orderBy(board.regDate.desc());
    }

    private long fetchCount(BoardCondition condition, Integer boardConfigId) {
        JPAQuery<Board> fetchSelect = fetchSelect(condition, boardConfigId);
        JPAQuery<Long> countQuery = fetchSelect.select(board.count());
        Long totalCount = countQuery.fetchOne();
        return totalCount == null ? 0 : totalCount;
    }

    private BooleanExpression containTitle(BoardCondition condition) {
        String title = condition.getTitle();
        if (StringUtils.isBlank(title)) {
            return null;
        }

        return board.title.contains(title);
    }

    private BooleanExpression eqBoardConfigId(Integer boardConfigId) {
        return board.boardConfig.boardConfigId.eq(boardConfigId);
    }
}
