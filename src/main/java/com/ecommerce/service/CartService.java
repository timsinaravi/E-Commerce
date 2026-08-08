package com.ecommerce.service;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.request.UpdateCartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;

import java.math.BigDecimal;

public interface CartService {

    CartResponseDto create(Long userId);

    CartResponseDto getCart(Long userId);

    CartItemResponseDto addCartItem(Long userId, CartItemRequestDto dto);

    CartItemResponseDto updateCartItem(Long userId, Long cartItemId, UpdateCartItemRequestDto dto);

    void removeCartItem(Long userId, Long cartItemId);

    void clearCart(Long userId);

    BigDecimal getCartTotal(Long userId);

}
