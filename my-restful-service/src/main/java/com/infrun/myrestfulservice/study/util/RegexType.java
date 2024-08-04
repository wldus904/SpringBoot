package com.infrun.myrestfulservice.study.util;

public class RegexType {
    // 최소 8자, 문자 1개 이상 + 숫자 1개 + 특수 문자 1개
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
}