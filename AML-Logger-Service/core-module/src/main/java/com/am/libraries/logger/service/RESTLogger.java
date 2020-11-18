package com.am.libraries.logger.service;

import com.am.libraries.logger.model.data.AppSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class RESTLogger extends AbstractLogger {

    public RESTLogger() {
    }

    public RESTLogger(String loggerName) {
        super(loggerName);
    }

    protected abstract String getLoggerName();

    @Override
    public void setAppSession(AppSession session) {
        ServletRequestAttributes servletReqAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletReqAttr.getRequest();
        request.setAttribute(AppSession.REST_SESSION, session);
    }
}
