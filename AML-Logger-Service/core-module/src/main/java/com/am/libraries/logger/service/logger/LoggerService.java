package com.am.libraries.logger.service.logger;

import com.am.libraries.logger.common.LoggerConfigManager;
import com.am.libraries.logger.service.RESTLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService extends RESTLogger {

    @Autowired
    public LoggerService(LoggerConfigManager loggerConfigManager) {
        super(loggerConfigManager.getDefaultLoggerName());
    }

    @Override
    protected String getLoggerName() {
        // This is ignored as Initializing using Constructor is used instead
        return "";
    }
}
