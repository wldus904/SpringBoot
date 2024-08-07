package com.infrun.myrestfulservice.study.controller;

import com.infrun.myrestfulservice.exception.UserDuplicateException;
import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.dto.StudentDto;
import com.infrun.myrestfulservice.study.dto.StudentLoginDto;
import com.infrun.myrestfulservice.study.dto.TokenDto;
import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.service.StudentJoinService;
import com.infrun.myrestfulservice.study.service.StudentLoginService;
import com.infrun.myrestfulservice.study.service.StudentService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentJoinService studentJoinService;
    @Autowired
    private StudentLoginService studentLoginService;

    @PostMapping("/join")
    public CommonResponse createStudent(@RequestBody StudentDto studentDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            studentJoinService.joinStudent(studentDto);
        } catch (UserDuplicateException e) {
            commonResponse.setError(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return commonResponse;
    }

    @PostMapping("/login")
    public CommonResponse loginStudent(@RequestBody StudentLoginDto studentLoginDto, HttpServletResponse response) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            studentLoginService.login(studentLoginDto, response);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @PostMapping("/reissue")
    public CommonResponse reissue(HttpServletRequest request) throws BadRequestException {
        TokenDto tokenDto = studentLoginService.reissue(request);
        return new CommonResponse(tokenDto);
    }

    @GetMapping
    public CommonResponse getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        return new CommonResponse(students);
    }

    @GetMapping("/{id}")
    public CommonResponse getStudentById(@PathVariable String id) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            Student student = studentService.getStudentById(id);
            commonResponse.setData(student);
        } catch (UserNotFoundException e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
