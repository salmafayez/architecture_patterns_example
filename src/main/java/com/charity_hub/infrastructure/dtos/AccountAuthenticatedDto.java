package com.charity_hub.infrastructure.dtos;

import java.util.UUID;

public record AccountAuthenticatedDto(
        UUID id,
        String deviceId,
        String deviceType,
        String deviceFCMToken
) implements AccountEventDto {

}
