package com.infrun.myrestfulservice.study.classroom.entiry;

import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "class_student")
public class ClassStudent {
    @ManyToMany
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToMany
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;
}
