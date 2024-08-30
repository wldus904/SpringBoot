package com.infrun.myrestfulservice.study.classroom.controller;

import com.infrun.myrestfulservice.study.classroom.dto.ClassroomCondition;
import com.infrun.myrestfulservice.study.classroom.dto.ClassroomDto;
import com.infrun.myrestfulservice.study.classroom.entiry.Classroom;
import com.infrun.myrestfulservice.study.classroom.service.ClassroomService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    private CommonResponse getClassrooms(@ModelAttribute ClassroomCondition condition) {
        CommonResponse commonResponse = new CommonResponse();

        try {
//            Page<ClassroomDto> classrooms = classroomService.findAllClassroom(condition);
            List<ClassroomDto> classrooms = classroomService.findAllClassroom(condition);
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
            ClassroomDto classroomDto = classroomService.findClassroomById(classroomId);
            commonResponse.setData(classroomDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
