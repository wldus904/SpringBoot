package com.infrun.myrestfulservice.study.repository;

import com.infrun.myrestfulservice.study.dto.BoardDto;
import com.infrun.myrestfulservice.study.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT new com.infrun.myrestfulservice.study.dto.BoardDto(" +
            "bc.boardConfigId, b.boardId, bc.title as boardName, b.title as boardTitle, " +
            "b.content, b.writerMemberId, b.status, b.refId, b.regDate, b.modDate) " +
            "FROM Board b " +
            "JOIN b.boardConfig bc")
    List<BoardDto> findAllByBoardConfigId(Integer boardConfigId);
}
