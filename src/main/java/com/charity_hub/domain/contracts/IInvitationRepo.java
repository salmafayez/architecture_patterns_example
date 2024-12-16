package com.charity_hub.domain.contracts;


import java.util.concurrent.CompletableFuture;

public interface IInvitationRepo {

    CompletableFuture<Boolean> hasInvitation(String mobileNumber);
}