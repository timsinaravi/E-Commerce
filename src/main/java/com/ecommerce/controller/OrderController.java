package com.ecommerce.controller;

import com.ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderResponseDto> placeOrder(@PathVariable Long userId) {

        OrderResponseDto response = orderService.placeOrder(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
