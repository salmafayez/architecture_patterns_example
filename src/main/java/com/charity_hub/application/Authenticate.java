package com.charity_hub.application;

import com.charity_hub.shared.abstractions.Command;

public record Authenticate(String idToken, String deviceId, String deviceType) implements Command {
}