package com.infrun.myrestfulservice.study.classroom.service;

import com.infrun.myrestfulservice.study.classroom.dto.ClassroomCondition;
import com.infrun.myrestfulservice.study.classroom.dto.ClassroomDto;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.classroom.repository.ClassroomDynamicRepository;
import com.infrun.myrestfulservice.study.classroom.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final ClassroomDynamicRepository classroomDynamicRepository;

    public Page<ClassroomDto> findAllClassroom (ClassroomCondition condition) {
        Page<Classroom> classrooms = classroomDynamicRepository.findAll(condition);
        return classrooms.map(ClassroomDto::toDto);
    }

    public ClassroomDto findClassroomById (Integer classroomId) {
        return ClassroomDto.toDto(classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id: " + classroomId)));
    }
}
