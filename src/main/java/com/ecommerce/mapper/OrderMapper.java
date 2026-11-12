package com.ecommerce.mapper;

import com.ecommerce.dto.response.OrderItemResponseDto;
import com.ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponseDto mapToDto(Order order) {

        OrderResponseDto dto = new OrderResponseDto();

        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponseDto> dtoList = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            dtoList.add(orderItemMapper.mapToDto(orderItem));
        }
        dto.setOrderItems(dtoList);

        return dto;

    }
}
