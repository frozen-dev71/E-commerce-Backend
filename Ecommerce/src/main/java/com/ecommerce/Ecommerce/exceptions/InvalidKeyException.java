package com.ecommerce.Ecommerce.exceptions;

public class InvalidKeyException extends Exception{

    public InvalidKeyException() {
    }

    public InvalidKeyException(String errmsg) {
        super(errmsg);
    }
}
