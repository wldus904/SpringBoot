package com.infrun.myrestfulservice.study.service;

import com.infrun.myrestfulservice.study.dto.TokenDto;
import com.infrun.myrestfulservice.study.entity.MemberRefreshToken;
import com.infrun.myrestfulservice.study.repository.MemberRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRefreshTokenService {
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;

    public boolean isExists(String refreshToken) {
        return memberRefreshTokenRepository.existsByRefreshToken(refreshToken);
    }

    public MemberRefreshToken getByToken(String refreshToken) {
        Optional<MemberRefreshToken> optionToken = memberRefreshTokenRepository.findOneByRefreshToken(refreshToken);
        return optionToken.orElseThrow(() -> new BadCredentialsException("잘못된 토큰 정보입니다."));
    }

    @Transactional
    public void saveTokenByTokenDto(String memberId, TokenDto tokenDto) {
        String refreshToken = tokenDto.getRefreshToken();
        LocalDateTime refreshTokeExpire = tokenDto.getRefreshTokenExpire();

        MemberRefreshToken memberRefreshToken = MemberRefreshToken.builder()
                .refreshToken(refreshToken)
                .memberId(memberId)
                .expireDate(refreshTokeExpire)
                .build();
        memberRefreshTokenRepository.save(memberRefreshToken);
    }

    @Transactional
    public void deleteByToken(String refreshToken) {
        memberRefreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    public List<MemberRefreshToken> getAllAlreadyExpire() {
        return memberRefreshTokenRepository.findAllByExpireDateBefore(LocalDateTime.now());
    }

    @Transactional
    public void deleteAll(List<MemberRefreshToken> refreshTokens) {
        memberRefreshTokenRepository.deleteAll();
    }
}
