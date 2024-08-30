package com.infrun.myrestfulservice.study.classroom.entiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "year")
    private int year;

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_section")
    private int classSection;

    @Column(name = "homeroom_teacher")
    private String homeroomTeacher;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "classroom")
    private List<ClassStudent> classStudent;

    @OneToMany(mappedBy = "classroom")
    private List<ClassStaff> classStaff;

    @PrePersist
    protected void onCreate() {
        this.regDate = LocalDateTime.now();
    }
}
