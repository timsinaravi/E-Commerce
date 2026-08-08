package com.ecommerce.mapper;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {


    private final ProductMapper productMapper;

    public CartItem mapToEntity(CartItemRequestDto dto) {

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(dto.getQuantity());


        return cartItem;

    }

    public CartItemResponseDto mapToDto(CartItem cartItem) {

        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setId(cartItem.getId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setProduct(productMapper.mapToDto(cartItem.getProduct()));

        return dto;

    }
}
