package com.charity_hub.infrastructure;

import com.charity_hub.domain.contracts.ILogger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//TODO we wanna use it in api layer => is it the right place
@Component
public class Logger implements ILogger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("[Charity-hub - App]");

    @Override
    public void log(String message) {
        logger.debug(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void errorLog(String message) {
        logger.error(message);
    }
}
