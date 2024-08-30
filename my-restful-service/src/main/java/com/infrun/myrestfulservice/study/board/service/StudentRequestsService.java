package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.dto.StudentRequestsDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.entity.StudentRequests;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import com.infrun.myrestfulservice.study.board.repository.BoardRepository;
import com.infrun.myrestfulservice.study.board.repository.StudentRequestsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentRequestsService {
    private final StudentRequestsRepository studentRequestsRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final BoardConfigRepository boardConfigRepository;
    private BoardConfigType boardConfigType = BoardConfigType.STUDENT_REQUESTS;

    @Transactional
    public StudentRequestsDto saveStudentRequests (StudentRequestsDto studentRequestsDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        BoardStatus boardStatus = BoardStatus.PUBLISHED;
        Board board = boardService.saveBoard(studentRequestsDto, boardConfig, boardStatus);

        StudentRequests studentRequests = StudentRequests.builder()
                .board(board)
                .requestsType(studentRequestsDto.getRequestsType())
                .build();
        return StudentRequestsDto.toDto(studentRequestsRepository.save(studentRequests));
    }

    public Page<BoardDto> getStudentRequests (BoardCondition condition) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.findAllBoard(condition, boardConfig.getBoardConfigId());
    }

    public BoardDto getStudentRequest (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.findBoardById(boardId, boardConfig.getBoardConfigId());
    }

    @Transactional
    public BoardDto updateStudentRequest (BoardDto boardDto, Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return boardService.updateBoard(boardId, boardDto, boardConfig.getBoardConfigId(), boardConfigType);
    }

    @Transactional
    public void deleteStudentRequest (Integer bordId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        boardService.deleteBoardById(bordId, boardConfig.getBoardConfigId());
    }
}
