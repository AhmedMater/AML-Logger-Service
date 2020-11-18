package com.am.libraries.logger.service;

import com.giza.libraries.common.model.enums.HttpHeaders;
import com.am.libraries.logger.model.data.AppSession;
import com.am.libraries.logger.model.data.AuthorizedUser;
import com.am.libraries.logger.model.enums.Source;
import com.am.libraries.logger.model.data.ILogCategories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLogger {
    protected Logger logger;

    public AbstractLogger(){
        String loggerName = this.getLoggerName();
        if(loggerName == null || loggerName.trim().equals(""))
            throw new IllegalArgumentException("Invalid Logger Name");

        this.logger = LogManager.getLogger(loggerName);
    }
    public AbstractLogger(String loggerName){
        if(loggerName == null || loggerName.trim().equals(""))
            throw new IllegalArgumentException("Invalid Logger Name");

        this.logger = LogManager.getLogger(loggerName);
    }

    public void pushSignature(Class clazz, String fnName){
        AppSession session = this.getAppSession();
        if(session != null) {
            session.pushSignature(clazz, fnName);
            this.setAppSession(session);
        }
    }
    public void popSignature(){
        AppSession session = this.getAppSession();
        if(session != null) {
            session.popSignature();
            this.setAppSession(session);
        }
    }
    public void setSessionCategory(ILogCategories category){
        AppSession session = this.getAppSession();
        if(session != null) {
            session.setCategory(category);
            this.setAppSession(session);
        }
    }
    public void addAttributeToSession(String key, Object value) {
        AppSession session = this.getAppSession();
        if(session != null) {
            session.addOtherValue(key, value);
            this.setAppSession(session);
        }
    }
    public AuthorizedUser getAuthorizedUser(){
        AppSession session = this.getAppSession();
        if(session != null) {
            return session.getUser();
        }else
            return null;
    }
    public AppSession getAppSession(){
        RequestAttributes reqAttributes =  RequestContextHolder.getRequestAttributes();
        AppSession session = null;
        if(reqAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            session = (AppSession) request.getAttribute(AppSession.REST_SESSION);

            if (session == null) {
                this.logger.error("No Application Session in REST Controller Attributes");
                this.logger.error(request.getRequestURI());

                session = new AppSession(Source.REST);
                session.setToken(request.getHeader(HttpHeaders.AUTHORIZATION.value()));
                session.setCurrentLang(request.getHeader(HttpHeaders.REQUEST_LANGUAGE.value()));
                session.setCorrelationID(request.getHeader(HttpHeaders.REQUEST_ID.value()));
            }
        } //else if(this.beanFactory.containsBean(AppSession.MESSAGING_SESSION))
            //session = this.beanFactory.getBean(AppSession.MESSAGING_SESSION, AppSession.class);
        else
            this.logger.error("Unhandled Session Scope");
        return session;
    }
    public String getCurrentScope(){
        RequestAttributes reqAttributes = RequestContextHolder.getRequestAttributes();
        if(reqAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            Object session = request.getAttribute(AppSession.REST_SESSION);

            if (session != null)
                return AppSession.REST_SESSION;
        } else
            logger.error("Not Supported Scope");
        return null;
    }

    abstract protected String getLoggerName();
    abstract protected void setAppSession(AppSession session);

    private String format() {
        AppSession session = this.getAppSession();
        if (session != null)
            return session.getLogPrefix();

        return "";
    }

    private void infoLog(String message, Object ... args) {
        String preLogString = this.format();
        String evaluatedMessage = MessageFormat.format(message, args);
        logger.info(preLogString + evaluatedMessage);
    }
    public void info(String message, Object ... args) {
        this.infoLog(message, args);
    }

    private void startDebugLog(Object... args) {
        String preLogString = this.format();
        StringBuilder message = new StringBuilder();
        if(args.length != 0) {
            if(args[0] != null)
                message.append((args[0].getClass().isArray()) ?
                        Arrays.toString((Object[]) args[0]) : args[0].toString());
            else
                message.append("Null");

            for (int i = 1; i < args.length; i++)
                if(args[i] != null)
                    message.append(", ").append( (args[i].getClass().isArray()) ?
                            Arrays.toString((Object[]) args[i]) : args[i].toString());
                else
                    message.append(", ").append("Null");

            message = new StringBuilder("Started with input: [" + message.toString() + "]");
        } else {
            message.append("Started");
        }

        logger.debug(preLogString + message.toString());
    }
    public void startDebug(Object... args) {
        this.startDebugLog(args);
    }

    private void endDebugLog(Object obj, boolean isOutputProvided) {
        String preLogString = this.format();
        String value;

        if(obj != null)
            value = obj.getClass().isArray() ? Arrays.toString((Object[]) obj) : obj.toString();
        else
            value = "Null";

        if(isOutputProvided)
            logger.debug(preLogString + "Ended successfully with result: [" + value + "]");
        else
            logger.debug(preLogString + "Ended successfully");

    }
    public void endDebug(Object obj) {
        this.endDebugLog(obj, true);
    }
    public void endDebug() {
        this.endDebugLog(null, false);
    }

    public void logArgument(Map<String, Object> map) {
        String preLogString = this.format();
        String value;
        StringBuilder logStatement = new StringBuilder(preLogString);

        for(String key: map.keySet()) {
            Object obj = map.get(key);
            if (obj != null)
                value = obj.getClass().isArray() ? Arrays.toString((Object[]) obj) : obj.toString();
            else
                value = "Null";

            logStatement.append("[").append(key).append(": ").append(value).append("] ");
        }
        logger.debug(logStatement.toString());
    }
    public void logArgument(String argLabel, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put(argLabel, obj);
        this.logArgument(map);
    }

    private void errorLog(Exception exp, String message, Object ... args) {
        String preLogString = this.format();
        String evaluatedMessage = (message != null) ? MessageFormat.format(message, args) : "";
        logger.error(preLogString + evaluatedMessage, exp);
    }
    public void error(Exception exp, String message, Object ... args) {
        this.errorLog(exp, message, args);
    }
    public void error(String message, Object ... args) {
        this.errorLog(null, message, args);
    }
    public void error(Exception exp) {
        this.errorLog(exp, null);
    }

    private void warnLog(String message, Object ... args) {
        String preLogString = this.format();
        String evaluatedMessage = (message != null) ? MessageFormat.format(message, args) : "";
        logger.warn(preLogString + evaluatedMessage);
    }
    public void warn(String message, Object ... args) {
        this.warnLog(message, args);
    }
}
