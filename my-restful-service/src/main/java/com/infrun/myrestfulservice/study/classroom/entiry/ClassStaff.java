package com.infrun.myrestfulservice.study.classroom.entiry;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "class_staff")
public class ClassStaff {
    // 복합키
    @EmbeddedId
    private ClassCombinedId id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberId") // 복합키 맵핑
    private Member member;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @MapsId("classroomId")
    private Classroom classroom;

    @Column(name = "subject_code")
    private String subjectCode;
}
