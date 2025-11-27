package com.lkm.tappingshop.item.controller;

import com.lkm.tappingshop.item.dto.ItemRead;
import com.lkm.tappingshop.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class itemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/api/item/{id}")
    public ResponseEntity<?> readOne(@PathVariable Integer id) {

        ItemRead item = itemService.findById(id);

        if (item == null) {
            return null;
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
