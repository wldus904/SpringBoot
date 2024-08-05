package com.infrun.myrestfulservice.config;

import com.infrun.myrestfulservice.security.CustomerAccessDeniedHandler;
import com.infrun.myrestfulservice.security.CustomerAuthenticationEntryPoint;
import com.infrun.myrestfulservice.security.JwtAuthenticationFilter;
import com.infrun.myrestfulservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // spring 설정 클래스
@EnableWebSecurity // spring security 활성화
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.infrun.myrestfulservice") // 최상위 package
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    // 아래 기재된 엔드포인트 인정 없이 접근
    private final static String[] PERMIT_ALL = {
            "/api/students/join",
            "/api/students/login",
//            "/api/students", // 나중에 주석처리
            "/api/students/reissue" // refresh token을 통한 재발급
    };

    // CSFR 공격으로부터 방어하기 위해 리소스 변경하는 delete, create등은 함부로 하지 못하도록 막혀있음
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // csrf 보안 사용 안함
                .csrf(AbstractHttpConfigurer::disable)
                // JWT 기반의 로그인 작성을 위해 spring security 기본 로그인(formLogin) 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // 사용자 정보를 http 헤더에 포함시키지 않고 더 복잡한 인증 방식을 사용하기 위해 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // session 관리 정책 설정(STATELESS: 세션 사용하지 않음)
                .sessionManagement((sessionManger) ->
                        sessionManger.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // PERMIT_ALL 엔드포인트 인증 없이 접근 가능하도록 설정
                .authorizeHttpRequests(request ->
                        request.requestMatchers(PERMIT_ALL)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                // UserNameAuthenticationFilter : POST 요청을 가로채어 사용자 이름과 비밀번호를 추출해 사용자 자격 증명 검증
                // UserNameAuthenticationFilter 전에 jwt token 관련 filter 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                // 인증, 인가 관련된 예외 처리 추가
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(new CustomerAccessDeniedHandler())
                        .authenticationEntryPoint(new CustomerAuthenticationEntryPoint())
                );
        return http.build();
    }

    // bean 등록, 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder는 강력한 해시 알고리즘 제공
        return new BCryptPasswordEncoder();
    }
}
