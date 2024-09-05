package com.infrun.myrestfulservice.study.classroom.entiry;

import com.infrun.myrestfulservice.study.member.entity.Member;
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

    @OneToOne
    @JoinColumn(name = "homeroom_teacher", referencedColumnName = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "classroom")
    private List<ClassStudent> classStudent;

    @OneToMany(mappedBy = "classroom")
    private List<ClassStaff> classStaff;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @PrePersist
    protected void onCreate() {
        this.regDate = LocalDateTime.now();
    }
}
