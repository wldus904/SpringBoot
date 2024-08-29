package com.infrun.myrestfulservice.study.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="member")
public class Member {
    @Id
    @Column(name="member_id", nullable = false)
    private String memberId;

//    @JsonIgnore
    @Column(name="password", nullable = false)
    private String password = "1234";

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name="grade", nullable = false)
    private int grade;

    @Column(name="reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name="mod_date", nullable = false)
    private LocalDateTime modDate;

    @PrePersist
    protected void onCreate() {
        regDate = LocalDateTime.now();
        modDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modDate = LocalDateTime.now();
    }

    @Builder
    public Member(String memberId, String password, String name, String address, String email, String phone, int grade) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
    }
}
