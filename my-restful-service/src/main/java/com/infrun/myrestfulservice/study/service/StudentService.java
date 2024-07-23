package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired // ?
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        long count = studentRepository.count() + 1;
        String studentIdStr = datePart + String.format("%02d", count);

        student.setStudentId(Integer.parseInt(studentIdStr));
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
