package com.infrun.myrestfulservice.security;

import com.infrun.myrestfulservice.study.constant.AuthConstant;
import com.infrun.myrestfulservice.study.member.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.expire}")
    private int ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refresh.expire}")
    private int REFRESH_TOKEN_EXPIRE_TIME;
    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;
    private static final String TOKEN_AUTH_KEY = "auth";

    @PostConstruct // 의존성 주입이 이루어진 후 초기화 수행
    public void init() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("secret key 가 없습니다.");
        }
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(String phone) {
        Date now = Calendar.getInstance().getTime();

        Date accessTokenExpireDate = DateUtils.addMilliseconds(now, ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpireDate = DateUtils.addMilliseconds(now, REFRESH_TOKEN_EXPIRE_TIME);
        String accessToken = getToken(phone, accessTokenExpireDate);
        String refreshToken = getToken(phone, refreshTokenExpireDate);

        LocalDateTime accessTokenExpire = accessTokenExpireDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime refreshTokenExpire = refreshTokenExpireDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return TokenDto.builder()
                .grantType(AuthConstant.TOKEN_TYPE)
                .accessToken(accessToken)
                .accessTokenExpire(accessTokenExpire)
                .refreshToken(refreshToken)
                .refreshTokenExpire(refreshTokenExpire)
                .build();
    }

    private String getToken(String subject, Date expireDate) {
        return Jwts.builder()
//                .claim("auth", authority)
                .setIssuedAt(expireDate)
                .setExpiration(expireDate)
                .setSubject(subject)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public void addTokenToCookie(HttpServletResponse response, TokenDto tokenDto) {
        response.addCookie(makeTokenToCookie("refreshToken", tokenDto.getRefreshToken(), REFRESH_TOKEN_EXPIRE_TIME));
        response.addCookie(makeTokenToCookie("accessToken", tokenDto.getAccessToken(), ACCESS_TOKEN_EXPIRE_TIME));
    }

    public void removeTokenToCookie(HttpServletResponse response) {
        response.addCookie(makeTokenToCookie("refreshToken", null, 0));
        response.addCookie(makeTokenToCookie("accessToken", null, 0));
    }

    private Cookie makeTokenToCookie (String tokenName, String token, int expireTime) {
        Cookie cookie = new Cookie(tokenName, token);
        cookie.setHttpOnly(true);  // JavaScript에서 접근 불가
        cookie.setSecure(true);    // HTTPS에서만 전송
        cookie.setPath("/");       // 애플리케이션 전체에 적용
        cookie.setMaxAge(expireTime / 1000);  // 쿠키 만료 기간 설정

        return cookie;
    }

    public Authentication getAuthentication(String accessToken) throws AccessDeniedException {
        Claims claims = getClaims(accessToken);
        String authority = (String) claims.get(TOKEN_AUTH_KEY);

        if (authority == null) {
            authority = "ROLE_USER";
        }

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<? extends GrantedAuthority> authorities = List.of(simpleGrantedAuthority);

        // 나중에 controller에서 Principal로 유저 정보에 접근 가능
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Claims getClaims(String token) throws AccessDeniedException {
        try {
            return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("getClaims Error: {}", e.getClass().getName());
            throw new AccessDeniedException("잘못된 요청입니다.");
        }
    }
}
