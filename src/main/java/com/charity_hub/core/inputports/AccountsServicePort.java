package com.charity_hub.core.inputports;

import com.charity_hub.core.services.Authenticate;
import com.charity_hub.core.services.AuthenticateResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface AccountsServicePort {
    CompletableFuture<AuthenticateResponse> authenticate(Authenticate command);
}