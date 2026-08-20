package com.ecommerce.dto.response;

import com.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private Status status;
    private List<OrderItemResponseDto> orderItems;
}
