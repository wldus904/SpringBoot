package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.entity.ExamScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamScoreRepository extends JpaRepository<ExamScore, Integer> {
}
