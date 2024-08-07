package com.infrun.myrestfulservice.study.repository;

import com.infrun.myrestfulservice.study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByPhone(String phone);
    boolean existsByMemberId(String memberId);
    Optional<Member> findByPhone(String phone);
    Optional<Member> findByMemberId(String id);
    Optional<Member> findByName(String name);
    Optional<Member> findByMemberIdAndName(String id, String name);
}
