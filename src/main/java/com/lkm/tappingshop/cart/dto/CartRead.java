package com.lkm.tappingshop.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter // ①
@Builder  // ②
public class CartRead {

    private Integer id; // ③
    private Integer itemId; // ④
}
