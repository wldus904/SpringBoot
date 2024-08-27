package com.infrun.myrestfulservice.study.classroom.entiry;

import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "class_staff")
public class ClassStaff {
    @ManyToMany
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column(name = "subject_code")
    private String subjectCode;
}
