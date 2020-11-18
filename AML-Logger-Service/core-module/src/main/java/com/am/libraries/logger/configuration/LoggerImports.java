package com.am.libraries.logger.configuration;

import com.am.libraries.logger.aspect.FunctionInputOutputLogAspect;
import com.am.libraries.logger.aspect.GenerateAppSessionAspect;
import com.am.libraries.logger.aspect.RESTLoggingAspect;
import com.am.libraries.logger.common.LoggerConfigManager;
import com.am.libraries.logger.service.logger.LoggerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({FunctionInputOutputLogAspect.class, GenerateAppSessionAspect.class, LoggerConfigManager.class,
        LoggerService.class, RESTLoggingAspect.class})
public class LoggerImports { }