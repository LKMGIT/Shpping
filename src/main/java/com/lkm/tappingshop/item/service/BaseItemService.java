package com.lkm.tappingshop.item.service;

import com.lkm.tappingshop.item.dto.ItemRead;
import com.lkm.tappingshop.item.entity.Item;
import com.lkm.tappingshop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemRead> findAll() {
        // 받아온 Item 들을 미리 지정해준 toRead를 이용하여 ItemRead 리스트로 변환
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllById(ids).stream().map(Item::toRead).toList();
    }

    @Override
    public ItemRead findById(Integer id) {
        return itemRepository.findById(id)
                .map(Item::toRead) // 엔티티 -> DTO
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. id=" + id));
    }

}
