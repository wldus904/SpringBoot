package com.infrun.myrestfulservice.security;

import com.infrun.myrestfulservice.study.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // OncePerRequestFilter
    // 동일한 request 안에서 한번만 실행되는 필터, 현재 프로젝트에선 jwt token 인증에 사용
    // api/A가 끝난 후 api/B로 리다이렉트 될 경우 Filter가 두번 호출되는 것을 막을 수 있음

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 인증이 필요 없는 경로를 설정
        if (requestURI.equals("/api/members/join")
                || requestURI.equals("/api/members/login")
                || requestURI.equals("/api/members/reissue")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            throw new AccessDeniedException("잘못된 요청입니다.");
        }

        String accessToken = Arrays.stream(cookies)
                .filter(cookie -> "accessToken".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("잘못된 요청입니다."));

        if (!StringUtils.isEmpty(accessToken)) {
            // 토큰이 유효하지 않은 경우 exception 발생l
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 인증에 성공했을 경우 SecurityContentHolder에 Authentication 값이 셋팅된 다음 필터로 넘어감
        filterChain.doFilter(request, response);
    }
}
