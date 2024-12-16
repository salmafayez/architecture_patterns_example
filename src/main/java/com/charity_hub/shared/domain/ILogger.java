package com.charity_hub.shared.domain;

public interface ILogger {
    void log(String message);
    void errorLog(String message);
}