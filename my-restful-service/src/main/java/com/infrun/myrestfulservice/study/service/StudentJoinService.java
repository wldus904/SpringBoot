package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.exception.UserDuplicateException;
import com.infrun.myrestfulservice.study.dto.StudentDto;
import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j // 로깅 자동 설정
@Service
@RequiredArgsConstructor
public class StudentJoinService {

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public void joinStudent(final StudentDto studentDto) throws UserDuplicateException {
        String phone = studentDto.getPhone();

        if (studentService.existByPhone(phone)) {
            throw new UserDuplicateException("이미 등록된 학생입니다.");
        }

        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        long count = studentRepository.count() + 1;
        String studentId = datePart + String.format("%02d", count);
        String encodedPassword = passwordEncoder.encode(studentDto.getPassword());

        studentDto.setStudentId(studentId);
        Student student = Student.builder()
                .studentId(studentDto.getStudentId())
                .password(encodedPassword)
                .studentName(studentDto.getStudentName())
                .address(studentDto.getAddress())
                .email(studentDto.getEmail())
                .phone(studentDto.getPhone())
                .build();

        studentRepository.save(student);
    }
}
