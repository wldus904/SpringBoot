package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.study.dto.BoardDto;
import com.infrun.myrestfulservice.study.entity.Board;
import com.infrun.myrestfulservice.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDto> getAllNotice () {
        return boardRepository.findAllByBoardConfigId(0);
    }
}
