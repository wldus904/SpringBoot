package com.infrun.myrestfulservice.security;

import com.infrun.myrestfulservice.study.constant.AuthConstant;
import com.infrun.myrestfulservice.study.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.expire}")
    private int ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refresh.expire}")
    private int REFRESH_TOKEN_EXPIRE_TIME;
    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;

    @PostConstruct // 의존성 주입이 이루어진 후 초기화 수행
    public void init() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("secret key 가 없습니다.");
        }
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(String email) {
        Date now = Calendar.getInstance().getTime();

        Date accessTokenExpireDate = DateUtils.addMilliseconds(now, ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpireDate = DateUtils.addMilliseconds(now, REFRESH_TOKEN_EXPIRE_TIME);
        String accessToken = getToken(email, accessTokenExpireDate);
        String refreshToken = getToken(email, refreshTokenExpireDate);

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
}
