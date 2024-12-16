package com.charity_hub.domain.exceptions;

public class InvalidAmountException extends DomainException {
    
    public InvalidAmountException() {
        super("");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}