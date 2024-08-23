package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.study.constants.BoardStatus;
import com.infrun.myrestfulservice.study.constants.BoardType;
import com.infrun.myrestfulservice.study.dto.BoardDto;
import com.infrun.myrestfulservice.study.entity.Board;
import com.infrun.myrestfulservice.study.entity.BoardConfig;
import com.infrun.myrestfulservice.study.repository.BoardConfigRepository;
import com.infrun.myrestfulservice.study.repository.BoardRepository;
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
    private BoardType boardType = BoardType.NOTICE;
    private final BoardRepository boardRepository;
    private final BoardConfigRepository boardConfigRepository;

    @Transactional
    public void saveNotice (BoardDto boardDto) {
        BoardStatus boardStatus = BoardStatus.PUBLISHED;
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardType.getCode());

        Board board = Board.builder()
                .boardConfig(boardConfig)
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writerMemberId(boardDto.getWriterMemberId())
                .status(boardStatus.getCode())
                .refId(boardDto.getRefId())
                .build();

        boardRepository.save(board);
    }

    public List<BoardDto> findAllNotice () {
        return boardRepository.findAllByBoardConfigId(boardType.getCode());
    }

    public Optional<Board> findNoticeById (Integer boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public Board updateNotice (Integer boardId, BoardDto boardDto) {
        Board board = findNoticeById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardDto.getBoardId()));
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardType.getCode());

        board.updateBoard(boardConfig, boardDto.getTitle(), boardDto.getContent(), boardDto.getStatus());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteNoticeById (Integer boardId) {
        boardRepository.deleteById(boardId);
    }
}
