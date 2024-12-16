package com.charity_hub.shared.domain.model;

import com.charity_hub.shared.domain.exceptions.InvalidAmountException;
import lombok.SneakyThrows;

public record Amount(int value, AmountType type) {
    private static final int MIN_AMOUNT = -9999999;
    private static final int MAX_AMOUNT = 9999999;

    @SneakyThrows
    public Amount {
        if (value < 0) {
            throw new InvalidAmountException(value + " is invalid, should be within " + MIN_AMOUNT + " - " + MAX_AMOUNT);
        }
    }

    public static Amount forNetwork(int value) {
        return new Amount(value, AmountType.NetworkDueAmount);
    }

    public static Amount forMember(int value) {
        return new Amount(value, AmountType.MemberDueAmount);
    }

    public Amount plus(Amount increment) {
        return new Amount(this.value + increment.value(), this.type);
    }

    public Amount minus(Amount decrement) {
        return new Amount(this.value - decrement.value(), this.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value &&
                type == amount.type;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }
}