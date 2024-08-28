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
@Table(name = "class_student")
public class ClassStudent {
    @EmbeddedId
    private ClassCombinedId id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @MapsId("memberId") // 복합키 맵핑
    private Member member;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @MapsId("classroomId") // 복합키 맵핑
    private Classroom classroom;
}
