package com.am.libraries.logger.model.data;

import com.am.libraries.logger.model.enums.Source;

import java.io.Serializable;
import java.util.*;

public class AppSession implements Serializable {
    public static final String REST_SESSION = "App-Session";

    private AuthorizedUser user;
    private String source;
    private String correlationID;
    private String currentLang;
    private String moduleID;
    private String jmsDestinationName;

    private Stack<String> signatures = new Stack<>();
    private Map<String, String> others;
    
    private String logPrefix;

    public AppSession() {
    }

    public AppSession(Source source, String correlationID, AuthorizedUser currentUser, String currentLang) {
        this.source = source != null ? source.name() : null;
        this.user = currentUser;
        this.correlationID = correlationID;
        this.currentLang = currentLang;
        this.setLogPrefix();
    }

    public AppSession(Source source, String correlationID) {
        this(source, correlationID, null, null);
    }

    public AppSession(Source source) {
        this(source, null, null, null);
    }


    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
        this.setLogPrefix();
    }
    public void setSource(Source source) {
        this.source = source.name();
        this.setLogPrefix();
    }

    public AuthorizedUser getUser() {
        return user;
    }
    public void setUser(AuthorizedUser user) {
        this.user = user;
        this.setLogPrefix();
    }

    public String getUserID(){
        if(this.user != null)
            return this.user.getId();
        else
            return null;
    }
    public void setUserID(String userID) {
        if(this.user == null)
            this.user = new AuthorizedUser();
        this.user.setId(userID);
        this.setLogPrefix();
    }

    public String getUsername(){
        if(this.user != null)
            return this.user.getUsername();
        else
            return null;
    }
    public void setUsername(String username) {
        if(this.user == null)
            this.user = new AuthorizedUser();
        this.user.setUsername(username);
        this.setLogPrefix();
    }

    public String getToken(){
        if(this.user != null)
            return this.user.getToken();
        else
            return null;
    }
    public void setToken(String token) {
        if(this.user == null)
            this.user = new AuthorizedUser();
        this.user.setToken(token);
    }

    public String getCorrelationID() {
        return correlationID;
    }
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
        this.setLogPrefix();
    }

    public String getCurrentLang() {
        return currentLang;
    }
    public void setCurrentLang(String currentLang) {
        this.currentLang = currentLang;
        this.setLogPrefix();
    }

    public String getModuleID() {
        return moduleID;
    }
    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
        this.setLogPrefix();
    }

    public String getJmsDestinationName() {
        return jmsDestinationName;
    }
    public void setJmsDestinationName(String jmsDestinationName) {
        this.jmsDestinationName = jmsDestinationName;
        this.setLogPrefix();
    }

    public Map<String, String> getOthers() {
        return others;
    }
    public void addOtherValue(String key, Object value) {
        if(this.others == null)
            this.others = new HashMap<>();
        this.others.put(key, value.toString());
        this.setLogPrefix();
    }
    public void setOthers(Map<String, String> others) {
        this.others = others;
        this.setLogPrefix();
    }

    public void pushSignature(Class clazz, String fnName){
        signatures.push(clazz.getCanonicalName() + "." + fnName + "()");
        this.setLogPrefix();
    }
    public void popSignature(){
        this.signatures.pop();
        this.setLogPrefix();
    }


    public String getLogPrefix() {
        return logPrefix;
    }
    private void setLogPrefix() {
        List<String> statements = new ArrayList<>();

        if (this.getSource() != null)
            statements.add("[" + this.getSource() + "]");

        if (this.getCorrelationID() != null)
            statements.add("[" + this.getCorrelationID() + "]");

        if (this.getCurrentLang() != null)
            statements.add("[" + this.getCurrentLang() + "]");

        if (this.getModuleID() != null)
            statements.add("[" + this.getModuleID() + "]");

        if (this.getUser() != null){
            List<String> userStatements = new ArrayList<>();

            if(this.getUser().getId() != null)
                userStatements.add(this.getUser().getId());

            if(this.getUser().getUsername() != null)
                userStatements.add(this.getUser().getUsername());

            StringBuilder _fullStatement = new StringBuilder(userStatements.get(0));
            for(int i=1; i<userStatements.size(); i++)
                _fullStatement.append(" - ").append(userStatements.get(i));

            statements.add("[" + _fullStatement.toString() + "]");
        }

        if(this.others != null)
            for (String key : this.getOthers().keySet())
                statements.add("[" + key + ": " + this.getOthers().get(key) + "]");

        if (this.getJmsDestinationName() != null)
            statements.add("[" + this.getJmsDestinationName() + "]");

        if(this.signatures != null && this.signatures.size() != 0)
            statements.add("[" + this.signatures.peek() + "]");

        StringBuilder fullStatement = new StringBuilder(statements.get(0));
        for(int i=1; i<statements.size(); i++)
            fullStatement.append(" ").append(statements.get(i));

        this.logPrefix = fullStatement.append(" ").toString();
    }

    @Override
    public String toString() {
        return "AppSession{" +
                "user=" + user + "\n" +
                ", source='" + source + "\'\n" +
                ", correlationID='" + correlationID + "\'\n" +
                ", currentLang='" + currentLang + "\'\n" +
                ", moduleID='" + moduleID + "\'\n" +
                ", jmsDestinationName='" + jmsDestinationName + "\'\n" +
                ", signatures=" + signatures + "\n" +
                ", others=" + others + "\n" +
                ", logPrefix='" + logPrefix + "\'\n" +
                '}';
    }
}
