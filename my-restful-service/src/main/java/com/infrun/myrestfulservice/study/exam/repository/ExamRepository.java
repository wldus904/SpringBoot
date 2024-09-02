package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
