package com.charity_hub.core.services;

public record AuthenticateResponse(String accessToken, String refreshToken) {
}