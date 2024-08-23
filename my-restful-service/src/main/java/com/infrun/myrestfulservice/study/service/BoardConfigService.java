package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.study.entity.BoardConfig;
import com.infrun.myrestfulservice.study.repository.BoardConfigRepository;
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
