package com.charity_hub.domain.contracts;

import com.charity_hub.domain.models.account.Account;
import com.charity_hub.domain.models.device.Device;

public interface IJWTGenerator {
    String generateAccessToken(Account account, Device device);

    String generateRefreshToken(Account account, Device device);
}