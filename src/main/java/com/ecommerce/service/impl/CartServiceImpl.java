package com.ecommerce.service.impl;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.request.UpdateCartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.exception.DuplicateCartException;
import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.mapper.CartMapper;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;


    @Override
    public CartResponseDto create(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getCart() != null) {
            throw new DuplicateCartException("Cart already exists");
        }

        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);

        Cart cartSaved = cartRepository.save(cart);
        return cartMapper.mapToDto(cartSaved);
    }

    @Override
    public CartResponseDto getCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        return cartMapper.mapToDto(cart);

    }


    @Override
    public CartItemResponseDto addCartItem(Long userId, CartItemRequestDto dto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CartItemResponseDto updateCartItem(Long userId, Long cartItemId, UpdateCartItemRequestDto dto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void clearCart(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public BigDecimal getCartTotal(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
