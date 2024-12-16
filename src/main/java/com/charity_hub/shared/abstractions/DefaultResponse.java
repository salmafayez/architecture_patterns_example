package com.charity_hub.shared.abstractions;

public record DefaultResponse(int id, String description) {

    public static DefaultResponse defaultResponse() {
        return new DefaultResponse(0, "success");
    }
}