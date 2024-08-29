package com.infrun.myrestfulservice.study.classroom.entiry;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 복합기 사용하기 위해서
@Embeddable
@Getter
@NoArgsConstructor
// 복합키를 만들기 위해서는 Serializable 인터페이스를 구현해야됨
public class ClassCombinedId implements Serializable {

    private String memberId;
    private Integer classroomId;
}