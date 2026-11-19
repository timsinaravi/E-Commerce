package com.ecommerce.controller;

import com.ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.security.service.CustomUserDetails;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@AuthenticationPrincipal CustomUserDetails currentUser) {

        OrderResponseDto response = orderService.placeOrder(currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/my")
public ResponseEntity<List<OrderResponseDto>> getMyOrders(@AuthenticationPrincipal CustomUserDetails currentUser) {

        return ResponseEntity.ok(orderService.getOrdersByUser(currentUser.getId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(@PathVariable Long userId) {

        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

}
