package com.lkm.tappingshop.item.dto;


import lombok.Builder;
import lombok.Getter;

// 화면에 뿌려주기 위한 상품 DTO
@Getter
@Builder
public class ItemRead {
    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}
