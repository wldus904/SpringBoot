package com.infrun.myrestfulservice.study.classroom.controller;

import com.infrun.myrestfulservice.study.classroom.dto.ClassroomDto;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.classroom.service.ClassroomService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    private CommonResponse getClassrooms() {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<ClassroomDto> classrooms = classroomService.findAllClassroom();
            commonResponse.setData(classrooms);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping("/{classroomId}")
    private CommonResponse getClassroom(@PathVariable Integer classroomId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            Optional<Classroom> classroom = classroomService.findClassroomById(classroomId);
            commonResponse.setData(classroom);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
