package com.filem.tech.Accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static Map<String, UserProfile> userBase = new HashMap<>();
    private static Map<String, UserProfile> sessionBase = new HashMap<>();

    public AccountService(){
    }

    public boolean AddNewUser(String login, String password, String email) {
        UserProfile user = new UserProfile(login, password, email);
        if (userBase.containsKey(login)){
            return false;
        }
        userBase.put(user.getLogin(), user);
        return true;
    }

    public boolean FindUser(String login){
        if(userBase.containsKey(login)){
            return true;
        }
        return false;
    }

    public boolean AuthorizateUser(UserProfile authProfile, String sessionID){
        UserProfile baseProfile = userBase.get(authProfile.getLogin());
        if(baseProfile != null){
            if(baseProfile.getPasssword().compareTo(authProfile.getPasssword()) == 0){
                sessionBase.put(sessionID, baseProfile);
                return true;
            }
            return false;
        }
        return false;
    }

    public String getLoginBySessionId(String sessionID){
        if(sessionBase.containsKey(sessionID)){
            return sessionBase.get(sessionID).getLogin();
        }
        return null;
    }
    public boolean CheckSessionId(String sessionID){
        if(sessionBase.containsKey(sessionID)){
            return true;
        }
        return false;
    }

    public boolean Quit(String sessionID){
        if(sessionBase.containsKey(sessionID)){
            sessionBase.remove(sessionID);
            return true;
        }
        return false;
    }
}
