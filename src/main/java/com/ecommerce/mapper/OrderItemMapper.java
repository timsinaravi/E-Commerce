package com.ecommerce.mapper;

import com.ecommerce.dto.response.OrderItemResponseDto;
import com.ecommerce.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderItemMapper {

    private final ProductMapper productMapper;

    public OrderItemResponseDto mapToDto(OrderItem orderItem) {

        OrderItemResponseDto dto = new OrderItemResponseDto();

        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setProduct(productMapper.mapToDto(orderItem.getProduct()));

        return dto;
    }

}
