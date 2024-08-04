package com.infrun.myrestfulservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // spring 설정 클래스
@EnableWebSecurity // spring security 활성화
@ComponentScan(basePackages = "com.infrun.myrestfulservice") // 최상위 package
public class SecurityConfig {
    // 아래 기재된 엔드포인트 인정 없이 접근
    private final static String[] PERMIT_ALL = {
            "/api/students/join",
            "/api/students/login",
            "/api/students",
            "/api/students/reissue" // refresh token을 통한 재발급
    };

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails newUser = User.withUsername("user")
                .password(passwordEncoder().encode("passw0rd"))
                .authorities("read")
                .build();

        userDetailsManager.createUser(newUser);
        return userDetailsManager;
    }

    // 비밀번호 암호화
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 인증을 거치지 않고 통과하도록 path 지정
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//    }

    // CSFR 공격으로부터 방어하기 위해 리소스 변경하는 delete, create등은 함부로 하지 못하도록 막혀있음
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 보안 사용 안함
        http.csrf(AbstractHttpConfigurer::disable)
                // PERMIT_ALL 엔드포인트 인증 없이 접근 가능하도록 설정
                .authorizeHttpRequests(request ->
                        request.requestMatchers(PERMIT_ALL)
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        return http.build();
    }
}
