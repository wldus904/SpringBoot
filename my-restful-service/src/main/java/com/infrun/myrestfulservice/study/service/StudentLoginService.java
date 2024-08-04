package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.security.JwtTokenProvider;
import com.infrun.myrestfulservice.study.dto.StudentLoginDto;
import com.infrun.myrestfulservice.study.dto.TokenDto;
import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentLoginService {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenDto login(StudentLoginDto studentLoginDto) throws UserNotFoundException {
        String studentId = studentLoginDto.getStudentId();
        if (!studentService.existById(studentId)) {
            throw new UserNotFoundException("로그인 정보를 확인해주세요.");
        }

        Student student = studentService.getStudentById(studentId);
        if (!passwordEncoder.matches(studentLoginDto.getPassword(), student.getPassword())) {
            throw new UserNotFoundException("비밀번호를 확인해주세요.");
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(studentId);
        return tokenDto;
    }
}
