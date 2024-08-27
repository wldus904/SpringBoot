package com.infrun.myrestfulservice.study.board.service;

import com.infrun.myrestfulservice.study.board.entity.BoardConfig;
import com.infrun.myrestfulservice.study.board.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigService {
    private final BoardConfigRepository boardConfigRepository;

    public List<BoardConfig> getAllBoardConfig() {
        return boardConfigRepository.findAll();
    }

    public BoardConfig getBoardConfigByIdType(int type) {
        return boardConfigRepository.getBoardConfigByType(type);
    }
}
