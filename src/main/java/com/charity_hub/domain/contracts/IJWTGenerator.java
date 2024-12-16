package com.charity_hub.domain.contracts;

import com.charity_hub.domain.model.account.Account;
import com.charity_hub.domain.model.device.Device;

public interface IJWTGenerator {
    String generateAccessToken(Account account, Device device);

    String generateRefreshToken(Account account, Device device);
}