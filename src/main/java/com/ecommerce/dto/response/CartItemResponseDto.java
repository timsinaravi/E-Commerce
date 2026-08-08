package com.ecommerce.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal subtotal;
}
