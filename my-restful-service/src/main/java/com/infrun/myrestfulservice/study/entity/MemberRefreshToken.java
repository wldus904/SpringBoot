package com.infrun.myrestfulservice.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
public class MemberRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private String memberId;
    private String refreshToken;
    private LocalDateTime expireDate;

    @Builder
    public MemberRefreshToken(String memberId, String refreshToken, LocalDateTime expireDate) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
        this.expireDate = expireDate;
    }
}
