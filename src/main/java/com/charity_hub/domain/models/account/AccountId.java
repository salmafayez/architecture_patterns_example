package com.charity_hub.domain.models.account;

import java.util.UUID;

public record AccountId(UUID value) {

    // Static factory method (replacing companion object)
    public static AccountId generate() {
        return new AccountId(UUID.randomUUID());
    }
}