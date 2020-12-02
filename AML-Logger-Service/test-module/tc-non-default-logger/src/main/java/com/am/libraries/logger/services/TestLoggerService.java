package com.am.libraries.logger.services;

import com.am.libraries.logger.libraries.logger.UserLogger;
import com.am.libraries.logger.model.annotations.LogInputOutput;
import com.am.libraries.logger.model.data.AppSession;
import com.am.libraries.logger.service.AbstractLogger;
import com.am.libraries.logger.service.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class TestLoggerService {
    private AbstractLogger logger;
    private UserLogger logger1;

    @Autowired
    public TestLoggerService(LoggerService logger) {
        this.logger = logger;
    }

    @LogInputOutput
    public String testLogInputOutput(String arg1, String arg2){
        String arg3 = arg1 + " " + arg2;
        String arg4 = arg1 + "-" + arg2;

        return arg3;
    }

    public String testLogInfo(String arg1, String arg2) {
        String arg3 = arg1 + " " + arg2;

        this.logger.info("Logic is executed successfully");
        this.logger.info("Concating 2 Strings {0}, {1} is executed successfully", arg1, arg2);
        return arg1;
    }

    public String testLogArguments(String arg1, String arg2) {
        String arg3 = arg1 + " " + arg2;
        String arg4 = arg1 + "-" + arg2;
        String arg5 = arg1 + "_" + arg2;

        Map<String, Object> arguments = new HashMap<String, Object>(){{
            put("Argument 3", arg3);
            put("Argument 4", arg4);
            put("Argument 5", arg5);
        }};

        this.logger.logArgument(arguments);

        String arg6 = arg1 + "~" + arg2;
        this.logger.logArgument("Argument 6", arg6);
        return arg3;
    }

    public String testLogError(String arg1, String arg2) {
        String arg3 = arg1 + " " + arg2;

        try{
            throw new Exception("Failed to continue Logic");
        } catch (Exception ex){
            this.logger.error("Logic failed to execute");
            this.logger.error("Logic of Concating 2 Strings {0}, {1} failed to execute", arg1, arg2);
            this.logger.error(ex, "Logic failed to execute");
            this.logger.error(ex,"Logic of Concating 2 Strings {0}, {1} failed to execute", arg1, arg2);
        }

        return arg3;
    }


public String getAppSession(String arg1, String arg2){
    RequestAttributes reqAttributes = RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = ((ServletRequestAttributes) reqAttributes).getRequest();
    AppSession session = (AppSession) request.getAttribute(AppSession.REST_SESSION);

    return arg1;
}
}