package com.am.libraries.logger.common;

import com.am.libraries.logger.service.AbstractLogger;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;

public class LoggerUtils {

    public static AbstractLogger getLogger(JoinPoint joinPoint) throws IllegalAccessException {
        AbstractLogger logger = null;

        Class myClass = joinPoint.getStaticPart().getSignature().getDeclaringType();
        for (Field field : myClass.getDeclaredFields()) {
            if (field.getType().equals(AbstractLogger.class)) {
                field.setAccessible(true);
                logger = (AbstractLogger) field.get(joinPoint.getTarget());
            }
        }

        //TODO: Check if there is multi Logger Classes in the same Class
        //TODO: Check if there is no Loggers in the Class
        return logger;
    }
}
