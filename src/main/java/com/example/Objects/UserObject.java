package com.example.Objects;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Sigal on 5/20/2016.
 */
public class UserObject {
    private int uid;
    private String name;
    private String userName;
    private String password;
    private SubjectObject currentSubject;
    private Date lastLogin;
    private Date lastPracticeDate;
    private int userType;
    private UserObject supervisor;
    private boolean active;


    public Date getLastPracticeDate() {
        return lastPracticeDate;
    }

    public void setLastPracticeDate(Date lastPracticeDate) {
        this.lastPracticeDate = lastPracticeDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SubjectObject getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(SubjectObject currentSubject) {
        this.currentSubject = currentSubject;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public UserObject getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(UserObject supervisor) {this.supervisor = supervisor;}

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}


}
