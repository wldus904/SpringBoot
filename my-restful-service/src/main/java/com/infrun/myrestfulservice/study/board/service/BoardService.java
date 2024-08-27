package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import com.infrun.myrestfulservice.study.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConfigRepository boardConfigRepository;

    @Transactional
    public Board saveBoard (BoardDto boardDto, BoardConfig boardConfig, BoardStatus boardStatus) {
        Board board = Board.builder()
                .boardConfig(boardConfig)
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writerMemberId(boardDto.getWriterMemberId())
                .status(boardStatus.getCode())
                .refId(boardDto.getRefId())
                .build();

        return boardRepository.save(board);
    }

    public List<BoardDto> findAllBoard (Integer boardConfigId) {
        return boardRepository.findAllByBoardConfigId(boardConfigId);
    }

    public Optional<Board> findBoardById (Integer boardId, Integer boardConfigId) {
        return boardRepository.findByBoardIdAndBoardConfigId(boardId, boardConfigId);
    }

    @Transactional
    public Board updateBoard (Integer boardId, BoardDto boardDto, Integer boardConfigId, BoardConfigType boardConfigType) {
        Board board = findBoardById(boardId, boardConfigId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardDto.getBoardId()));
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());

        board.updateBoard(boardConfig, boardDto.getTitle(), boardDto.getContent(), boardDto.getStatus());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoardById (Integer boardId, Integer boardConfigId) {
        boardRepository.deleteByBoardIdAndBoardConfigId(boardId, boardConfigId);
    }
}
