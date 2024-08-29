package com.infrun.myrestfulservice.study.board.repository;

import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT new com.infrun.myrestfulservice.study.board.dto.BoardDto(" +
            "bc.boardConfigId, b.boardId, bc.title as boardName, b.title as boardTitle, " +
            "b.content, b.writerMemberId, b.status, b.refId, b.regDate, b.modDate) " +
            "FROM Board b " +
            "JOIN b.boardConfig bc " +
            "WHERE bc.boardConfigId = :boardConfigId")  // boardConfigId로 필터링
    List<BoardDto> findAllByBoardConfigId(Integer boardConfigId);

    @Query("SELECT b FROM Board b JOIN b.boardConfig bc " +
            "WHERE b.boardId = :boardId AND bc.boardConfigId = :boardConfigId")
    Optional<Board> findByBoardIdAndBoardConfigId(Integer boardId, Integer boardConfigId);

    @Modifying
    @Query("DELETE FROM Board b WHERE b.boardId = :boardId AND b.boardConfig.boardConfigId = :boardConfigId")
    void deleteByBoardIdAndBoardConfigId(Integer boardId, Integer boardConfigId);
}
