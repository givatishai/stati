package com.example.Services.impl;

import com.example.Objects.UserObject;
import com.example.Objects.UserQuestionObject;
import com.example.Persist;
import com.example.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Timestamp;
import java.util.List;
import java.util.Date;
/**
 * Created by Sigal on 5/16/2016.
 */
public class UserManagerImpl implements UserManager{

    @Autowired
    private Persist persist;


    public boolean checkCredentials (String userName, String password) {
        if (userName != null && password != null) {
            return userName.equals("shai") && password.equals("123");
        }
        return false;
    }

    public UserObject loadUser(Integer uid) {
        return (UserObject) persist.load(UserObject.class, uid);
    }

    public void addNewUser(String name, String userName, String password, int type) {
        UserObject user = new UserObject();
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(password);
        user.setUserType(type);
        UserObject supervisor = loadUser(1);
        user.setSupervisor(supervisor);
        user.setActive(false);
        persist.save(user);

    }

    public UserObject getUserByEmailAndPassword (String email, String password) {
        return persist.getUserByEmailAndPassword(email, password);
    }

    public List<UserQuestionObject> getUserQuestionsByUid(int uid) {
        return persist.getUserQuestionsByUid(uid);
    }



    @Override
    public void updateUser(UserObject user) {
       user.setLastLogin(new Date());
        persist.save(user);
    }

    public long countUsers(){
        return persist.countUsers();
    }

    public List<UserObject> getMonitoredUsers(int uid){
        return persist.getMonitoredUsers(uid);
    }

    public List<UserObject> getAllRegularUsers() {
    return persist.getAllRegularUsers();}

   // public List<UserObject> getAllStudents(){
  //      return persist.getAllStudents();

    public UserObject changeSupervisor(int uid,int supervisor){
        return persist.changeSupervisor(uid,supervisor);
    }

    public List<UserObject> getAllSupervisors() { return persist.getAllSupervisors();}

    public List<UserObject> getAllPendingUsers(){
        return persist.getAllPendingUsers();
    }

}
