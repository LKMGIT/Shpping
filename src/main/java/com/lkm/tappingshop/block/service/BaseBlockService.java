package com.lkm.tappingshop.block.service;

import com.lkm.tappingshop.block.entity.Block;
import com.lkm.tappingshop.block.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseBlockService implements BlockService {

    private final BlockRepository blockRepository;

    // 토큰 차단 데이터 삽입
    @Override
    public void add(String token) {
        blockRepository.save(new Block(token));
    }

    // 토큰 차단 데이터가 있는지 확인
    @Override
    public boolean has(String token) {
        return blockRepository.findByToken(token).isPresent();
    }
}