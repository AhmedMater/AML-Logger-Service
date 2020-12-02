package com.am.libraries.logger.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class LoggerConfigManager {
    @Value("${aml.logger.useDefaultLogger:#{true}}")
    private Boolean useDefaultLogger;

    @Value("${aml.logger.defaultLoggerName:#{'Service-Logger'}}")
    private String defaultLoggerName;

    @Value("${aml.logger.logRESTRequests:#{false}}")
    private Boolean logRESTRequests;

    @Value("${aml.logger.appSession.generate.uuidRequestID-inCaseNotProvided:#{true}}")
    private Boolean generateUUIDRequestID;

    public Boolean getIsUseDefaultLogger() {
        return useDefaultLogger;
    }
    public void setIsUseDefaultLogger(Boolean useDefaultLogger) {
        this.useDefaultLogger = useDefaultLogger;
    }

    public String getDefaultLoggerName() {
        return defaultLoggerName;
    }
    public void setDefaultLoggerName(String defaultLoggerName) {
        this.defaultLoggerName = defaultLoggerName;
    }

    public Boolean getLogRESTRequests() {
        return logRESTRequests;
    }
    public void setLogRESTRequests(Boolean logRESTRequests) {
        this.logRESTRequests = logRESTRequests;
    }

    public Boolean getGenerateUUIDRequestID() {
        return generateUUIDRequestID;
    }
    public void setGenerateUUIDRequestID(Boolean generateUUIDRequestID) {
        this.generateUUIDRequestID = generateUUIDRequestID;
    }
}
