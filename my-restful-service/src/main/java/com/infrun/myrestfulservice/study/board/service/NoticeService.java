package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.dto.NoticeDto;
import com.infrun.myrestfulservice.study.board.dto.NoticeListDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private BoardConfigType boardConfigType = BoardConfigType.NOTICE;
    private final BoardService boardService;
    private final BoardConfigRepository boardConfigRepository;

    @Transactional
    public NoticeDto saveNotice (BoardDto boardDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        BoardStatus boardStatus = BoardStatus.PUBLISHED;
        Board board = boardService.saveBoard(boardDto, boardConfig, boardStatus);
        return NoticeDto.toDto(board);
    }

    public Page<NoticeListDto> findAllNotice (BoardCondition boardCondition) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        Page<Board> boardPages = boardService.findAllBoard(boardCondition, boardConfig.getBoardConfigId());
        return boardPages.map(NoticeListDto::toDto);
    }

    public NoticeDto findNoticeById (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return NoticeDto.toDto(boardService.findBoardById(boardId, boardConfig.getBoardConfigId()));
    }

    @Transactional
    public NoticeDto updateNotice (Integer boardId, BoardDto boardDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return NoticeDto.toDto(boardService.updateBoard(boardId, boardDto, boardConfig.getBoardConfigId(), boardConfigType));
    }

    @Transactional
    public void deleteNoticeById (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        boardService.deleteBoardById(boardId, boardConfig.getBoardConfigId());
    }
}
