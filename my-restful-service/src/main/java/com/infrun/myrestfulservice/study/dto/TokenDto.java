package com.infrun.myrestfulservice.study.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpire;
    private LocalDateTime refreshTokenExpire;
}
