package com.example.Services;

import com.example.Objects.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Sigal on 5/21/2016.
 */
@Transactional
public interface GeneralManager {

    public List<SubjectObject> getAllSubjects();

    public List<UserScoreObject> getTopThree();

    public List<TutorObject> getAvailableTutors();

    public List<SubjectObject> getAllSubjectsOrdered();

    public SubjectObject loadSubject(int oid);

    public List<ConfigObject> getConfig();

    public List<SentMessageObject> getUnreadMessages();

    public void addNewMessage(UserObject sender,String message, String title);

    public void markMessageAsRead(SentMessageObject message);
}