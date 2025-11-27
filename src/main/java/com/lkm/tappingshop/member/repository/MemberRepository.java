package com.lkm.tappingshop.member.repository;

import com.lkm.tappingshop.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 아이디, 비밀번호를 이용한 회원 조회기능
    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);
}
