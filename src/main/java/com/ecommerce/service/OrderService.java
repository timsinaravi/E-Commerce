package com.ecommerce.service;

import com.ecommerce.dto.response.OrderResponseDto;

public interface OrderService {

    OrderResponseDto placeOrder(Long userId);

}
