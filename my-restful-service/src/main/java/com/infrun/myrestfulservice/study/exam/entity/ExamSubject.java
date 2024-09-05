package com.infrun.myrestfulservice.study.exam.entity;

import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.member.entity.Member;
import com.infrun.myrestfulservice.study.subject.entity.Subject;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exam_subject")
public class ExamSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_subject_id")
    private Integer examSubjectId;

    @OneToOne
    @JoinColumn(name = "subject_code", nullable = false)
    private Subject subject;

    @Column(name = "exam_id")
    private Integer examId;

//    @ManyToOne
//    @JoinColumn(name = "exam_id", nullable = false)
//    private Exam exam;

    @OneToMany(mappedBy = "examSubjectId")
    private List<ExamScore> ExamScore;

    @Builder
    public ExamSubject(Integer examSubjectId, Integer examId, Subject subject) {
        this.examSubjectId = examSubjectId;
        this.examId = examId;
        this.subject = subject;
    }
}
