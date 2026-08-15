package com.ecommerce.controller;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    @PostMapping("/carts/{userId}")
    public ResponseEntity<CartResponseDto> createCart(@PathVariable Long userId) {

        CartResponseDto response = cartService.create(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/carts/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId) {

        CartResponseDto response = cartService.getCart(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cartitems/{userId}")
    public ResponseEntity<CartItemResponseDto> addCartItem(@PathVariable Long userId, @Valid @RequestBody CartItemRequestDto dto) {

        CartItemResponseDto response = cartService.addCartItem(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
