package com.infrun.myrestfulservice.study.board.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
public enum StudentRequestType {
    QUESTION("질문", 0),
    COUNSEL("상담", 1),
    Suggestion("건의", 2);

    private String title;
    private int code;

    StudentRequestType(String title, int code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCode() {
        return this.code;
    }

    public static StudentRequestType fromCode(int code) {
        return Arrays.stream(StudentRequestType.values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
