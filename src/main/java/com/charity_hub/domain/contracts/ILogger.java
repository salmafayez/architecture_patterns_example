package com.charity_hub.domain.contracts;

public interface ILogger {

    void log(String message);

    void warn(String message);

    void errorLog(String message);
}