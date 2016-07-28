package com.example.Services;

import com.example.Objects.UserObject;
import com.example.Objects.UserQuestionObject;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sigal on 5/20/2016.
 */
@Transactional
public interface UserManager {
    public boolean checkCredentials (String userName, String password);

    public void addNewUser(String name, String userName, String password, int type);

    public UserObject getUserByEmailAndPassword (String email, String password);

    public UserObject loadUser(Integer uid);

    public List<UserQuestionObject> getUserQuestionsByUid(int uid);

    public void updateUser(UserObject user);

    public long countUsers();

    public List<UserObject> getMonitoredUsers(int uid);

    public List<UserObject> getAllRegularUsers();

  //  public List<UserObject> getAllStudents(int uid);

    public UserObject changeSupervisor(int uid,int supervisor);

    public List<UserObject> getAllSupervisors();

    public List<UserObject> getAllPendingUsers();

}

