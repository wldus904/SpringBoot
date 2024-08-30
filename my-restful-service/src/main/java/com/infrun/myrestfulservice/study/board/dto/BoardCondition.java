package com.infrun.myrestfulservice.study.board.dto;

import lombok.Data;

@Data
public class BoardCondition {
    private String title;
    private int size = 10;
    private int page = 1;
}
