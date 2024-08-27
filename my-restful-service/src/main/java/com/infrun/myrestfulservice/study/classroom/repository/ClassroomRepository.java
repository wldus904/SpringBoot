package com.infrun.myrestfulservice.study.classroom.repository;

import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
}
