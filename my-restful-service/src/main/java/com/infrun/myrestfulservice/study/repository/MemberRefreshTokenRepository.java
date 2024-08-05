package com.infrun.myrestfulservice.study.repository;

import com.infrun.myrestfulservice.study.entity.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {
    boolean existsByRefreshToken(String refreshToken);
    Optional<MemberRefreshToken> findOneByRefreshToken(String refreshToken);
    void deleteByRefreshToken(String refreshToken);
    List<MemberRefreshToken> findAllByExpireDateBefore(LocalDateTime dateTime);
}
