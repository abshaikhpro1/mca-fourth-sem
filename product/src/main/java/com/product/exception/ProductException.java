package com.product.exception;

public class ProductException extends  RuntimeException{

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
