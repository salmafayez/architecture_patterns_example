package com.charity_hub.domain.contracts;

import com.charity_hub.domain.model.account.Account;

import java.util.concurrent.CompletableFuture;

public interface IAccountRepo {
    CompletableFuture<Account> getByMobileNumber(String mobileNumber);

    CompletableFuture<Void> save(Account account);

    CompletableFuture<Boolean> isAdmin(String mobileNumber);
}