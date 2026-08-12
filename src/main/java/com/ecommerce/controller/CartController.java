package com.ecommerce.controller;

import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartResponseDto> createCart(@PathVariable Long userId) {

        CartResponseDto response = cartService.create(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {

        CartResponseDto response = cartService.getCart(userId);
        return ResponseEntity.ok(response);
    }

}
