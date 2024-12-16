package com.charity_hub.core.exceptions;

public class InvalidAmountException extends DomainException {
    
    public InvalidAmountException() {
        super("");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}