package com.charity_hub.shared.domain.model;

public class Pair<T, U> {
    public final U second;

    public Pair(T t, U u) {
        this.second = u;
    }
}