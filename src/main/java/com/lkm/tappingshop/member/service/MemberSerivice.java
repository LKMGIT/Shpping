package com.lkm.tappingshop.member.service;


import com.lkm.tappingshop.member.entity.Member;

public interface MemberSerivice {

    //회원 저장
    void save(String name, String loginId, String loginPw);

    //회원 데이터 조회
    Member find(String loginId, String loginPw);
}
