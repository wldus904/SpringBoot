package com.infrun.myrestfulservice.study.board.entity;

import com.infrun.myrestfulservice.study.board.dto.StudentRequestsDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board_student_requests")
public class StudentRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_student_requests_id")
    private Integer boardStudentRequestsId;

    @Column(name = "requests_type")
    private int requestsType;

    @OneToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public StudentRequests (Board board, int requestsType) {
        this.board = board;
        this.requestsType = requestsType;
    }
}
