package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.study.dto.StudentDto;
import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    public boolean existByPhone (String phone) {
        return studentRepository.existsByPhone(phone);
    }
    public boolean existById (String studentId) {
        return studentRepository.existsByStudentId(studentId);
    }

    public Student getStudent(String id, String studentName) {
        if (!id.isEmpty() && !studentName.isEmpty()) {
            return getStudentByStudentIdAndStudentName(id, studentName);
        } else if (!id.isEmpty()) {
            return getStudentById(id);
        } else {
            return getStudentByStudentName(studentName);
        }
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public Student getStudentByStudentName(String studentName) {
        return studentRepository.findByStudentName(studentName)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public Student getStudentByStudentIdAndStudentName(String id, String studentName) {
        return studentRepository.findByStudentIdAndStudentName(id, studentName)
                .orElseThrow(() -> new UserNotFoundException("해당 학생를 찾을 수 없습니다."));
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }
}
