package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.constant.BoardConfigType;
import com.infrun.myrestfulservice.study.board.constant.BoardStatus;
import com.infrun.myrestfulservice.study.board.dto.BoardCondition;
import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import com.infrun.myrestfulservice.study.board.repository.BoardDynamicRepository;
import com.infrun.myrestfulservice.study.board.repository.BoardRepository;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardDynamicRepository boardDynamicRepository;
    private final BoardConfigRepository boardConfigRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Board saveBoard (BoardDto boardDto, BoardConfig boardConfig, BoardStatus boardStatus) {

        System.out.println("1111111111111111111" + boardDto);
        Member member = memberRepository
                .findById(boardDto.getWriterMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardDto.getWriterMemberId()));

        System.out.println("22222222222222222222");

        Board board = Board.builder()
                .boardConfig(boardConfig)
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .member(member)
                .status(boardStatus.getCode())
                .refId(boardDto.getRefId())
                .build();

        return boardRepository.save(board);
    }

    public Page<Board> findAllBoard (BoardCondition boardCondition, Integer boardConfigId) {
        return boardDynamicRepository.findAllByBoardConfigId(boardCondition, boardConfigId);
    }

    public Board findBoardById (Integer boardId, Integer boardConfigId) {
        return boardRepository.findByBoardIdAndBoardConfigId(boardId,boardConfigId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
    }

    @Transactional
    public Board updateBoard (Integer boardId, BoardDto boardDto, Integer boardConfigId, BoardConfigType boardConfigType) {
        Board board = boardRepository.findByBoardIdAndBoardConfigId(boardId, boardConfigId)
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
