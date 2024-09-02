package com.infrun.myrestfulservice.study.exam.repository;

import com.infrun.myrestfulservice.study.exam.entity.ExamSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSubjectRepository extends JpaRepository<ExamSubject, Integer> {
}
