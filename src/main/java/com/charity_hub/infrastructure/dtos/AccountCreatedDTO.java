package com.charity_hub.infrastructure.dtos;

import java.util.UUID;

public record AccountCreatedDTO(UUID id, String mobileNumber) implements AccountEventDto {

}
