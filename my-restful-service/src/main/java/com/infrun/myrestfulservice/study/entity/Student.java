package com.infrun.myrestfulservice.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="student_id", nullable = false)
    private String studentId;

//    @JsonIgnore
    @Column(name="password", nullable = false)
    private String password = "1234";

    @Column(name="student_name", nullable = false)
    private String studentName;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone", nullable = false)
    private String phone;

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
    public Student(String studentId, String password, String studentName, String address, String email, String phone) {
        this.studentId = studentId;
        this.password = password;
        this.studentName = studentName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
