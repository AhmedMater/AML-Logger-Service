package com.am.libraries.logger.configuration;

import com.am.libraries.logger.aspect.FunctionInputOutputLogAspect;
import com.am.libraries.logger.aspect.GenerateAppSessionAspect;
import com.am.libraries.logger.common.LoggerConfigManager;
import com.am.libraries.logger.service.logger.LoggerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableLogging.LoggerImports.class)
public @interface EnableLogging {

    @Configuration
    @Import({FunctionInputOutputLogAspect.class, GenerateAppSessionAspect.class, LoggerConfigManager.class,
            LoggerService.class})
    class LoggerImports { }
}


