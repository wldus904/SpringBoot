package com.infrun.myrestfulservice.study.board.repository;

import com.infrun.myrestfulservice.study.board.entity.StudentRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRequestsRepository extends JpaRepository<StudentRequests, Integer> {
}
