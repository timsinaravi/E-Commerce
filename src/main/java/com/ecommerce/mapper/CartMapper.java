package com.ecommerce.mapper;

import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CartMapper {

    private CartItemMapper cartItemMapper;

    public CartResponseDto mapToDto(Cart cart){

        CartResponseDto dto = new CartResponseDto();

        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());

        List<CartItemResponseDto> dtoList = new ArrayList<>();
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            dtoList.add(cartItemMapper.mapToDto(cartItem));
        }
        dto.setCartItems(dtoList);

        return dto;
    }


}
