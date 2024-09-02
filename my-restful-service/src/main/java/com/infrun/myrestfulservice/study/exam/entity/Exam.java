package com.infrun.myrestfulservice.study.exam.entity;

import com.infrun.myrestfulservice.study.exam.dto.ExamDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "semester")
    private int semester;

    @Column(name = "title")
    private String title;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @OneToMany(mappedBy = "exam")
    private List<ExamSubject> examSubject;

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
    public Exam(int semester, String title) {
        this.semester = semester;
        this.title = title;
    }
}
