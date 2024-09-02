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

    @Column(name = "tester")
    private String tester;

    @Column(name = "exam_date")
    private LocalDate examDate;

    @Column(name = "exam_order")
    private int examOrder;

    @Column(name = "exam_duration_minute")
    private int examDurationMinute;

    @Column(name = "grade")
    private int grade;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @OneToMany(mappedBy = "examSubject")
    private List<ExamScore> ExamScore;

    @Builder
    public ExamSubject(Exam exam, Subject subject, String tester, LocalDate examDate, int examOrder, int examDurationMinute, int grade) {
        this.exam = exam;
        this.subject = subject;
        this.tester = tester;
        this.examDate = examDate;
        this.examOrder = examOrder;
        this.examDurationMinute = examDurationMinute;
        this.grade = grade;
    }
}
