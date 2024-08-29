package com.infrun.myrestfulservice.study.board.repository;

import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.infrun.myrestfulservice.study.board.entity.QBoard.board;
import static com.infrun.myrestfulservice.study.board.entity.QBoardConfig.boardConfig;

@Repository
@RequiredArgsConstructor
public class BoardDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<Board> findAllByBoardConfigId(Integer boardConfigId, BoardCondition condition, Pageable pageable) {
        JPAQuery<Board> selectQuery = fetchSelect(condition)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        long count = fetchCount(condition);

        return PageableExecutionUtils.getPage(selectQuery.fetch(), pageable, () -> count);
    }

    private JPAQuery<Board> fetchSelect(BoardCondition condition) {
        return jpaQueryFactory
                .selectFrom(board)
                .join(board.boardConfig, boardConfig)
                .where(
                        containTitle(condition)
                ).orderBy(board.regDate.desc());
    }

    private long fetchCount(BoardCondition condition) {
        JPAQuery<Board> fetchSelect = fetchSelect(condition);
        JPAQuery<Long> countQuery = fetchSelect.select(board.count());
        Long totalCount = countQuery.fetchOne();
        return totalCount == null ? 0 : totalCount;
    }

    private BooleanExpression containTitle(BoardCondition condition) {
        String title = condition.getSearchWord();
        if (StringUtils.isEmpty(title)) {
            return null;
        }

        return board.title.contains(title);
    }
}
