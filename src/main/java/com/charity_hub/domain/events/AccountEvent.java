package com.charity_hub.domain.events;

import com.charity_hub.shared.domain.model.DomainEvent;

public sealed interface AccountEvent extends DomainEvent permits AccountAuthenticated, AccountCreated {
}