package com.charity_hub.infrastructure.dtos;

import com.charity_hub.domain.events.AccountAuthenticated;
import com.charity_hub.domain.events.AccountCreated;
import com.charity_hub.domain.events.AccountEvent;

public sealed interface AccountEventDto permits AccountAuthenticatedDto, AccountCreatedDTO {

    static AccountEventDto from(AccountEvent accountEvent) {
        if (accountEvent instanceof AccountCreated event) {
            return new AccountCreatedDTO(
                    event.id().value(),
                    event.mobileNumber().value()
            );
        }

        if (accountEvent instanceof AccountAuthenticated event) {
            return new AccountAuthenticatedDto(
                    event.id().value(),
                    event.deviceId().value(),
                    event.deviceType().value(),
                    event.deviceFCMToken() != null ? event.deviceFCMToken().getValue() : null
            );
        }

        throw new IllegalArgumentException("Unknown accountEvent type");
    }
}