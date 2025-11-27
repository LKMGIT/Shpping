package com.lkm.tappingshop.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginRequest {
    private String loginId;
    private String loginPw;
}
