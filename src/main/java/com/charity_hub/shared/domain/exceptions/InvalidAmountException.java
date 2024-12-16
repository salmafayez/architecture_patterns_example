package com.charity_hub.shared.domain.exceptions;

import com.charity_hub.shared.exceptions.DomainException;

public class InvalidAmountException extends DomainException {
    
    public InvalidAmountException() {
        super("");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}