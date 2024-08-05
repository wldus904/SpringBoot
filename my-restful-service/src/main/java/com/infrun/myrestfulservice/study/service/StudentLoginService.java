package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.security.JwtTokenProvider;
import com.infrun.myrestfulservice.study.constant.AuthConstant;
import com.infrun.myrestfulservice.study.dto.StudentLoginDto;
import com.infrun.myrestfulservice.study.dto.TokenDto;
import com.infrun.myrestfulservice.study.entity.MemberRefreshToken;
import com.infrun.myrestfulservice.study.entity.Student;
import com.infrun.myrestfulservice.study.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentLoginService {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRefreshTokenService memberRefreshTokenService;

    @Transactional
    public TokenDto login(StudentLoginDto studentLoginDto) throws UserNotFoundException {
        String studentId = studentLoginDto.getStudentId();
        if (!studentService.existById(studentId)) {
            throw new UserNotFoundException("로그인 정보를 확인해주세요.");
        }

        Student student = studentService.getStudentById(studentId);
        if (!passwordEncoder.matches(studentLoginDto.getPassword(), student.getPassword())) {
            throw new UserNotFoundException("비밀번호를 확인해주세요.");
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(student.getPhone());

        memberRefreshTokenService.saveTokenByTokenDto(student.getStudentId(), tokenDto);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(HttpServletRequest request) throws BadRequestException {
        String requestHeader = request.getHeader(AuthConstant.AUTH_HEADER);
        if (StringUtils.isEmpty(requestHeader)) {
            throw new BadRequestException("잘못된 요청입니다.");
        }

        String token = TokenUtil.parsingToken(requestHeader);
        if (!memberRefreshTokenService.isExists(token)) {
            throw new BadRequestException("잘못된 요청입니다.");
        }

        MemberRefreshToken refreshToken = memberRefreshTokenService.getByToken(token);
        LocalDateTime expireDate = refreshToken.getExpireDate();
        if (expireDate.isBefore(LocalDateTime.now())) {
            memberRefreshTokenService.deleteByToken(token);
            throw new BadRequestException("잘못된 요청입니다.");
        }

        String studentId = refreshToken.getMemberId();
        Student student = studentService.getStudentById(studentId);

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(student.getPhone());
        memberRefreshTokenService.saveTokenByTokenDto(student.getStudentId(), tokenDto);
        memberRefreshTokenService.deleteByToken(token);
        return tokenDto;
    }
}
