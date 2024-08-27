package com.infrun.myrestfulservice.study.classroom.service;

import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.classroom.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public List<Classroom> findAllClassroom () {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findClassroomById (Integer classroomId) {
        return classroomRepository.findById(classroomId);
    }
}
