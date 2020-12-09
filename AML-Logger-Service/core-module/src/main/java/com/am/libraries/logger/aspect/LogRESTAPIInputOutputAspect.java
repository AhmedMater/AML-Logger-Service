package com.am.libraries.logger.aspect;

import com.am.libraries.logger.service.logger.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class LogRESTAPIInputOutputAspect implements Ordered {
    @Autowired(required = false)
    private LoggerService logger;

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void logRESTRequest(JoinPoint joinPoint) throws Throwable {
//        if(!loggerConfigManager.getLogRESTRequests())
//            return;

        RequestAttributes reqAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) reqAttributes).getRequest();

//        if(loggerConfigManager.getIsUseDefaultLogger())
//            logger = defaultLogger;
//        else
//            logger = LoggerUtils.getLogger(joinPoint);

        if(logger != null){
            logger.pushSignature(getClass(), "logRESTRequest");
            this.logger.logArgument("Request URL", request.getServletPath() + request.getRequestURI());
            this.logger.logArgument("Request URL2", request.getRequestURL());
            this.logger.logArgument("Request Method", request.getMethod());

            if(request.getQueryString() != null)
                this.logger.logArgument("Request Query String", request.getQueryString());

            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                logger.logArgument("Header Name: " + headerName, headerValue);
            }
            logger.popSignature();
        }
    }

}
