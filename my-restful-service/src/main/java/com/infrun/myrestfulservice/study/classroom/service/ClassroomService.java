package com.infrun.myrestfulservice.study.classroom.service;

import com.infrun.myrestfulservice.study.classroom.dto.ClassroomDto;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.classroom.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public List<ClassroomDto> findAllClassroom () {
        List<Classroom> classroom = classroomRepository.findAll();
        return classroom.stream()
                .map(ClassroomDto::toDto)
                .collect(Collectors.toList());
    }

    public ClassroomDto findClassroomById (Integer classroomId) {
        return ClassroomDto.toDto(classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found with id: " + classroomId)));
    }
}
