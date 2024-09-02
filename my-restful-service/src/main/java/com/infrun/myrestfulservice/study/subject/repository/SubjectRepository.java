package com.infrun.myrestfulservice.study.subject.repository;

import com.infrun.myrestfulservice.study.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
