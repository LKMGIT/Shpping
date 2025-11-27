package com.lkm.tappingshop.account.helper;

import com.lkm.tappingshop.account.dto.AccountJoinRequest;
import com.lkm.tappingshop.account.dto.AccountLoginRequest;
import com.lkm.tappingshop.account.etc.AccountConstants;
import com.lkm.tappingshop.common.util.HttpUtils;
import com.lkm.tappingshop.member.entity.Member;
import com.lkm.tappingshop.member.service.MemberSerivice;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component // ①
@RequiredArgsConstructor // ②
public class SessionAccountHelper implements AccountHelper {

    private final MemberSerivice memberService; // ③

    // 회원가입
    @Override
    public void join(AccountJoinRequest joinReq) { // ④
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }

    // 로그인
    @Override
    public String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res) { // ④
        Member member = memberService.find(loginReq.getLoginId(), loginReq.getLoginPw());

        // 회원 데이터가 없으면
        if (member == null) {
            return null;
        }

        HttpUtils.setSession(req, AccountConstants.MEMBER_ID_NAME, member.getId());
        return member.getLoginId();
    }

    // 회원 아이디 조회
    @Override
    public Integer getMemberId(HttpServletRequest req) { // ④
        Object memberId = HttpUtils.getSession(req, AccountConstants.MEMBER_ID_NAME);

        if (memberId != null) {
            return (int) memberId;
        }

        return null;
    }

    // 로그인 여부 확인
    @Override
    public boolean isLoggedIn(HttpServletRequest req) { // ④
        return getMemberId(req) != null;
    }

    // 로그아웃
    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res) { // ④
        HttpUtils.removeSession(req, AccountConstants.MEMBER_ID_NAME);
    }
}
