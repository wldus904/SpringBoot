package com.infrun.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // setter(final이면 만들어지지 않음), getter가 만들어짐
@AllArgsConstructor // 모든 property를 가지는 생성자 생성
public class HelloWorldBean {
    private final  String message;
//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}
