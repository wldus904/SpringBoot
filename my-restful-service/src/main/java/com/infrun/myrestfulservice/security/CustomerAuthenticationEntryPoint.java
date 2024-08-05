package com.infrun.myrestfulservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // HttpStatus 401 Unauthorized는 사용자가 인증되지 않거나 유효한 인증 정보가 부족한 경우

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);      // JSON 타입 반환
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());   // 한국어 허용

        CommonResponse commonResponse = new CommonResponse(HttpStatus.UNAUTHORIZED);
        PrintWriter responseWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        responseWriter.write(objectMapper.writeValueAsString(commonResponse));
        responseWriter.flush();
        responseWriter.close();
    }
}
