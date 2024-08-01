package com.infrun.myrestfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor // default 생성자
@AllArgsConstructor // 전체를 다 가진 생성자
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

    @OneToMany(mappedBy = "user")
    private List<Post> post;

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
