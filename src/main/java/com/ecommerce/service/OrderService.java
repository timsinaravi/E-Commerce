package com.ecommerce.service;

import com.ecommerce.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto placeOrder(Long userId);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrder(Long id);

    List<OrderResponseDto> getOrdersByUser(Long userId);
}
