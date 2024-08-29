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

    @Column(name = "subject_code")
    private String subjectCode;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @MapsId("memberId") // 복합키 맵핑
    private Member member;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @MapsId("classroomId")
    private Classroom classroom;
}
