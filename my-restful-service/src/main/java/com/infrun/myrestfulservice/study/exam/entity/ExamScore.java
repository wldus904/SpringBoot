package com.infrun.myrestfulservice.study.exam.entity;

import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
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
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_subject_id")
    private Integer examSubjectId;

//    @ManyToOne
//    @JoinColumn(name = "exam_id", nullable = false)
//    private Exam exam;

//    @ManyToOne
//    @JoinColumn(name = "exam_subject_id", nullable = false)
//    private ExamSubject examSubject;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @PrePersist
    protected void onCreate() {
        regDate = LocalDateTime.now();
        modDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modDate = LocalDateTime.now();
    }

    @Builder
    public ExamScore (Integer examScoreId, Integer examId, Integer examSubjectId, Member member, int score, LocalDateTime regDate, LocalDateTime modDate) {
        this.examScoreId = examScoreId;
        this.examId = examId;
        this.examSubjectId = examSubjectId;
        this.member = member;
        this.score = score;
        this.regDate = (regDate == null) ? LocalDateTime.now() : regDate;
        this.modDate = (modDate == null) ? LocalDateTime.now() : modDate;
    }
}