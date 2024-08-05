package com.infrun.myrestfulservice.security;

import com.infrun.myrestfulservice.study.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
        String authHeader = request.getHeader("Authorization");
        String token = TokenUtil.parsingToken(authHeader);
        if (!StringUtils.isEmpty(token)) {
            // 토큰이 유효하지 않은 경우 exception 발생
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 인증에 성공했을 경우 SecurityContentHolder에 Authentication 값이 셋팅된 다음 필터로 넘어감
        filterChain.doFilter(request, response);
    }
}
