package com.infrun.myrestfulservice.study.exam.entity;

import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exam_score")
public class ExamScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_score_id")
    private Integer examScoreId;

    @Column(name = "score")
    private int score;

    @Column(name = "reg_date")
    private LocalDateTime reg_date;

    @Column(name = "mod_date")
    private LocalDateTime mod_date;

    @ManyToOne
    @JoinColumn(name = "exam_subject_id", nullable = false)
    private ExamSubject examSubject;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
