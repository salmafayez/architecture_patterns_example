package com.charity_hub.core.outputports;


import java.util.concurrent.CompletableFuture;

public interface IInvitationRepo {

    CompletableFuture<Boolean> hasInvitation(String mobileNumber);
}