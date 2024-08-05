package com.infrun.myrestfulservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CustomerAccessDeniedHandler extends AccessDeniedHandlerImpl {
    // HttpStatus 403 Forbidden은 서버가 요청을 애해했지만 사용자 권한이 없는 경우


    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        CommonResponse commonResponse = new CommonResponse(HttpStatus.UNAUTHORIZED);
        PrintWriter responseWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        responseWriter.write(objectMapper.writeValueAsString(commonResponse));
        responseWriter.flush();
        responseWriter.close();
    }
}
