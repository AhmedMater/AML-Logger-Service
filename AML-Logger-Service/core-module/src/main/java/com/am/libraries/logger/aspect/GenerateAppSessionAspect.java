package com.am.libraries.logger.aspect;

import com.giza.libraries.common.model.enums.HttpHeaders;
import com.am.libraries.logger.model.data.AppSession;
import com.am.libraries.logger.model.enums.Source;
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
import java.util.UUID;

@Aspect
@Component
public class GenerateAppSessionAspect implements Ordered{

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE-1 ;
    }

    @Autowired
    public GenerateAppSessionAspect(){

    }

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void generateAppSession(JoinPoint joinPoint) {
        RequestAttributes reqAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) reqAttributes).getRequest();

        AppSession session = new AppSession();
        session.setSource(Source.REST);

        String lang = request.getHeader(HttpHeaders.REQUEST_LANGUAGE.value());
        if(lang != null && !lang.trim().isEmpty())
            session.setCurrentLang(lang);

        String requestID = request.getHeader(HttpHeaders.REQUEST_ID.value());
        if(requestID != null && !requestID.trim().isEmpty())
            session.setCorrelationID(requestID);
        else
            session.setCorrelationID(UUID.randomUUID().toString());

        String moduleID = request.getHeader(HttpHeaders.MODULE_ID.value());
        if(moduleID != null && !moduleID.trim().isEmpty())
            session.setModuleID(moduleID);

        String authToken = request.getHeader(HttpHeaders.AUTHORIZATION.value());
        if(authToken != null && !authToken.trim().isEmpty()) {
            if (authToken.startsWith("Bearer"))
                session.setToken(authToken.substring("Bearer ".length()));
            else if (authToken.startsWith("Basic"))
                session.setToken(authToken.substring("Basic ".length()));
            else
                session.setToken(authToken);
        }

        request.setAttribute(AppSession.REST_SESSION, session);
    }
}
