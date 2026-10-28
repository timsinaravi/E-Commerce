package com.ecommerce.exception;

public class CategoryDeletionNotAllowedException extends RuntimeException {
    public CategoryDeletionNotAllowedException(String message) {
        super(message);
    }
}
