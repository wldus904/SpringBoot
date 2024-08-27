package com.infrun.myrestfulservice.study.board.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
public enum BoardConfigType {
    NOTICE("공지사항", 0),
    STUDENT_REQUESTS("학생 게시판", 1);

    private final String title;
    private final int code;

    BoardConfigType(String title, int code) {
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
