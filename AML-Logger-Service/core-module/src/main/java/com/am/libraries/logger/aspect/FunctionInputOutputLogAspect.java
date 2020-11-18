package com.am.libraries.logger.aspect;

import com.am.libraries.logger.common.LoggerConfigManager;
import com.am.libraries.logger.common.LoggerUtils;
import com.am.libraries.logger.model.data.AppSession;
import com.am.libraries.logger.service.AbstractLogger;
import com.am.libraries.logger.service.logger.LoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FunctionInputOutputLogAspect {

    private AbstractLogger logger;

    @Autowired(required = false)
    private LoggerService defaultLogger;

    @Autowired(required = false)
    private LoggerConfigManager loggerConfigManager;

    @Autowired
    public FunctionInputOutputLogAspect(){

    }

    @Around("@annotation(com.am.libraries.logger.model.annotations.LogInputOutput)")
    public Object logFunctionInputOutput(ProceedingJoinPoint joinPoint) throws Throwable {
        if(loggerConfigManager.getIsUseDefaultLogger())
            logger = defaultLogger;
        else
            logger = LoggerUtils.getLogger(joinPoint);

        if(logger == null)
            return joinPoint.proceed();

        String scope = this.logger.getCurrentScope();

        if(scope != null) {
            switch (scope) {
                case AppSession.REST_SESSION:
                    return this.logFunctionInputOutput(logger, joinPoint);
                default:
                    return joinPoint.proceed();
            }
        } else
            return joinPoint.proceed();
    }

    private Object logFunctionInputOutput(AbstractLogger logger, ProceedingJoinPoint joinPoint) throws Throwable {
        Signature sig = joinPoint.getSignature();
        Class clazz = sig.getDeclaringType();
        String fnName = sig.getName();
        logger.pushSignature(clazz, fnName);

        Object[] params = joinPoint.getArgs();
        if(params.length == 0)
            logger.startDebug();
        else
            logger.startDebug(params);

        Object obj = joinPoint.proceed();

        boolean isVoid = sig.toLongString().matches("^.+ void .+$");
        if (!isVoid)
            logger.endDebug(obj);
        else
            logger.endDebug();

        logger.popSignature();
        return obj;
    }

}
