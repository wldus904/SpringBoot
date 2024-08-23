package com.infrun.myrestfulservice.study.repository;


import com.infrun.myrestfulservice.study.entity.BoardConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardConfigRepository extends JpaRepository<BoardConfig, Integer> {
    BoardConfig getBoardConfigByType(int type);
}
