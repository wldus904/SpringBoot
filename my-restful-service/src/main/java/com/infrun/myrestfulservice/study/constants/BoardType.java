package com.infrun.myrestfulservice.study.constants;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
public enum BoardType {
    NOTICE("공지사항", 0),
    ONE_ON_ONE("1:1 상담", 1);

    private final String title;
    private final int code;

    BoardType(String title, int code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle () {
        return this.title;
    }

    public int getCode () {
        return this.code;
    }
}
