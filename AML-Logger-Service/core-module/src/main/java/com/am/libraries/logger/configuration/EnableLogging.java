package com.am.libraries.logger.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LoggerImports.class)
public @interface EnableLogging {
}
