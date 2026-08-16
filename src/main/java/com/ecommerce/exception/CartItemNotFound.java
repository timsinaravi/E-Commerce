package com.ecommerce.exception;

public class CartItemNotFound extends RuntimeException{

    public CartItemNotFound(String message) {
        super(message);
    }
}
