package com.infrun.myrestfulservice.study.board.dto;

import lombok.Data;

@Data
public class BoardCondition {
    private String searchWord;
    private int page;
    private int size;
}
