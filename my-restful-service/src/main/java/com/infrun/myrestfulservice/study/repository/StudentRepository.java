package com.infrun.myrestfulservice.study.repository;

import com.infrun.myrestfulservice.study.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByPhone(String phone);
    boolean existsByStudentId(String studentId);
    Optional<Student> findByPhone(String phone);
    Optional<Student> findByStudentName(String studentName);
    Optional<Student> findByStudentIdAndStudentName(String id, String studentName);
}
