package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private BoardConfigType boardConfigType = BoardConfigType.NOTICE;
    private final BoardService boardService;
    private final BoardConfigRepository boardConfigRepository;

    @Transactional
    public Board saveNotice (BoardDto boardDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        BoardStatus boardStatus = BoardStatus.PUBLISHED;
        return boardService.saveBoard(boardDto, boardConfig, boardStatus);
    }

    public List<BoardDto> findAllNotice () {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.findAllBoard(boardConfig.getBoardConfigId());
    }

    public Optional<Board> findNoticeById (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.findBoardById(boardId, boardConfig.getBoardConfigId());
    }

    @Transactional
    public Board updateNotice (Integer boardId, BoardDto boardDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.updateBoard(boardId, boardDto, boardConfig.getBoardConfigId(), boardConfigType);
    }

    @Transactional
    public void deleteNoticeById (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        boardService.deleteBoardById(boardId, boardConfig.getBoardConfigId());
    }
}
