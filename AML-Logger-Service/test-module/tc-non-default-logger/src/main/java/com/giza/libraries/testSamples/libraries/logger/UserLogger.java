package com.giza.libraries.testSamples.libraries.logger;

import com.am.libraries.logger.service.RESTLogger;
import org.springframework.stereotype.Service;

@Service
public class UserLogger extends RESTLogger {

    @Override
    protected String getLoggerName() {
        return "User-Logger";
    }
}
