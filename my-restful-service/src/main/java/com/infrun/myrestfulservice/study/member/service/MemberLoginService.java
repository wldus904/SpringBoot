package com.infrun.myrestfulservice.study.member.service;

import com.infrun.myrestfulservice.exception.UserNotFoundException;
import com.infrun.myrestfulservice.security.JwtTokenProvider;
import com.infrun.myrestfulservice.study.member.dto.MemberLoginDto;
import com.infrun.myrestfulservice.study.member.dto.TokenDto;
import com.infrun.myrestfulservice.study.member.entity.MemberRefreshToken;
import com.infrun.myrestfulservice.study.member.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRefreshTokenService memberRefreshTokenService;

    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto, HttpServletResponse response) throws UserNotFoundException {
        String memberId = memberLoginDto.getMemberId();
        if (!memberService.existById(memberId)) {
            throw new UserNotFoundException("로그인 정보를 확인해주세요.");
        }

        Member member = memberService.getMemberById(memberId);
        if (!passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
            throw new UserNotFoundException("비밀번호를 확인해주세요.");
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(member.getPhone());

        memberRefreshTokenService.saveTokenByTokenDto(member.getMemberId(), tokenDto);
        jwtTokenProvider.addTokenToCookie(response, tokenDto);
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
        // 쿠키를 사용하고 있어서 주석처리
//        String requestHeader = request.getHeader(AuthConstant.AUTH_HEADER);
//        if (StringUtils.isEmpty(requestHeader)) {
//            throw new BadRequestException("잘못된 요청입니다.");
//        }

//        String token = TokenUtil.parsingToken(requestHeader);
//        if (!memberRefreshTokenService.isExists(token)) {
//            throw new BadRequestException("잘못된 요청입니다.");
//        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new BadRequestException("잘못된 요청입니다.");
        }

        String token = Arrays.stream(cookies)
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new BadRequestException("잘못된 요청입니다."));

        MemberRefreshToken refreshToken = memberRefreshTokenService.getByToken(token);
        LocalDateTime expireDate = refreshToken.getExpireDate();
        if (expireDate.isBefore(LocalDateTime.now())) {
            memberRefreshTokenService.deleteByToken(token);
            jwtTokenProvider.removeTokenToCookie(response);
            throw new BadRequestException("잘못된 요청입니다.");
        }

        String memberId = refreshToken.getMemberId();
        Member member = memberService.getMemberById(memberId);

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(member.getPhone());
        memberRefreshTokenService.saveTokenByTokenDto(member.getMemberId(), tokenDto);
        memberRefreshTokenService.deleteByToken(token);
        jwtTokenProvider.addTokenToCookie(response, tokenDto);
        return tokenDto;
    }
}
