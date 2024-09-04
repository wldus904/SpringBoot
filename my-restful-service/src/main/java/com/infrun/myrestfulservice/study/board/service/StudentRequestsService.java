package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.dto.StudentRequestDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.entity.StudentRequests;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import com.infrun.myrestfulservice.study.board.repository.StudentRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentRequestsService {
    private final StudentRequestsRepository studentRequestsRepository;
    private final BoardService boardService;
    private final BoardConfigRepository boardConfigRepository;
    private BoardConfigType boardConfigType = BoardConfigType.STUDENT_REQUESTS;

    @Transactional
    public StudentRequestDto saveStudentRequests (StudentRequestDto studentRequestDto) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        BoardStatus boardStatus = BoardStatus.PUBLISHED;
        Board board = boardService.saveBoard(studentRequestDto, boardConfig, boardStatus);

        StudentRequests studentRequests = StudentRequests.builder()
                .board(board)
                .requestsType(studentRequestDto.getRequestType().getCode())
                .build();
        studentRequestsRepository.save(studentRequests);

        return StudentRequestDto.toDto(board);
    }

    public Page<StudentRequestDto> getStudentRequests (BoardCondition condition) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        Page<Board> boardPages = boardService.findAllBoard(condition, boardConfig.getBoardConfigId());
        return boardPages.map(StudentRequestDto::toDto);
    }

    public StudentRequestDto getStudentRequest (Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return StudentRequestDto.toDto(boardService.findBoardById(boardId, boardConfig.getBoardConfigId()));
    }

    @Transactional
    public StudentRequestDto updateStudentRequest (BoardDto boardDto, Integer boardId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        return StudentRequestDto.toDto(boardService.updateBoard(boardId, boardDto, boardConfig.getBoardConfigId(), boardConfigType));
    }

    @Transactional
    public void deleteStudentRequest (Integer bordId) {
        BoardConfig boardConfig = boardConfigRepository.getBoardConfigByType(boardConfigType.getCode());
        boardService.deleteBoardById(bordId, boardConfig.getBoardConfigId());
    }
}
