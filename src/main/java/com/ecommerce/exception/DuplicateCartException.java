package com.ecommerce.exception;

public class DuplicateCartException extends RuntimeException{

    public DuplicateCartException(String message) {
        super(message);
    }
}
