package com.inventario.exception;

public class SpringInventoryException extends RuntimeException {
    public SpringInventoryException(String exMessage) {
        super(exMessage);
    }
}
