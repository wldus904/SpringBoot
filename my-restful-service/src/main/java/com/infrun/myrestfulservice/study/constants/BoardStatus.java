package com.infrun.myrestfulservice.study.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
public enum BoardStatus {
    PUBLISHED("게시", 1),
    DELETE("삭제", 0);

    private final String title;
    private final int code;

    BoardStatus (String title, int code) {
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
