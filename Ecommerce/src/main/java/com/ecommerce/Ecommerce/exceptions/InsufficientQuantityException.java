package com.ecommerce.Ecommerce.exceptions;

public class InsufficientQuantityException extends Exception{
    public InsufficientQuantityException() {
    }

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
