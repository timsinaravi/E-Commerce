package com.ecommerce.service.impl;

import com.ecommerce.dto.response.OrderResponseDto;
import com.ecommerce.entity.*;
import com.ecommerce.enums.Status;
import com.ecommerce.exception.*;
import com.ecommerce.mapper.OrderMapper;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartItemRepository cartItemRepository;


    @Override
    public OrderResponseDto placeOrder(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new CartItemNotFound("Cart item not found");
        }

        for (CartItem cartItem : cartItems) {
            if (cartItem.getQuantity() > cartItem.getProduct().getStock()) {
                throw new OutOfStockException("Out of stock");
            }
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.PLACED);
        BigDecimal totalAmount = new BigDecimal(0);

        for (CartItem cartItem : cartItems) {
            totalAmount = totalAmount.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            cartItem.getProduct().setStock(cartItem.getProduct().getStock() - cartItem.getQuantity());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        }

        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }
        cartItems.clear();
        return orderMapper.mapToDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> dtoList = new ArrayList<>();

        for (Order order : orders) {
            dtoList.add(orderMapper.mapToDto(order));
        }
        return dtoList;
    }

    @Override
    public OrderResponseDto getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        return orderMapper.mapToDto(order);
    }


}
