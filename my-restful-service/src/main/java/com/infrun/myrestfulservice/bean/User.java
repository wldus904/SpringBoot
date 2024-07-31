package com.infrun.myrestfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})// response에 이 값들을 포함하지 않음
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="이름은 2글자 이상 입력해 주세요.")
    private String name;

    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
    private Date joinDate;

//    @JsonIgnore // response에 이 값을 포함하지 않음
    private String password;

//    @JsonIgnore // response에 이 값을 포함하지 않음
    private String ssn;
}
