package com.ecommerce.controller;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.request.UpdateCartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.security.service.CustomUserDetails;
import com.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;


    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(@AuthenticationPrincipal CustomUserDetails currentUser) {

        CartResponseDto response = cartService.create(currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(@AuthenticationPrincipal CustomUserDetails currentUser) {

        CartResponseDto response = cartService.getCart(currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemResponseDto> addCartItem(@AuthenticationPrincipal CustomUserDetails currentUser, @Valid @RequestBody CartItemRequestDto dto) {

        CartItemResponseDto response = cartService.addCartItem(currentUser.getId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItemResponseDto> updateCartItem(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable Long cartItemId, @Valid @RequestBody UpdateCartItemRequestDto dto) {
        CartItemResponseDto response = cartService.updateCartItem(currentUser.getId(), cartItemId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@AuthenticationPrincipal CustomUserDetails currentUser, @PathVariable Long cartItemId) {
         cartService.removeCartItem(currentUser.getId(), cartItemId);
         return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal CustomUserDetails currentUser) {
        cartService.clearCart(currentUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getCartTotal(@AuthenticationPrincipal CustomUserDetails currentUser) {

        BigDecimal response = cartService.getCartTotal(currentUser.getId());
        return ResponseEntity.ok(response);
    }

}
