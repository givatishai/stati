package com.example.Services.impl;

import com.example.Objects.*;
import com.example.Persist;
import com.example.Services.GeneralManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Sigal on 5/21/2016.
 */

@Service
public class GeneralManagerImpl implements GeneralManager {

    @Autowired
    private Persist persist;

    public List<SubjectObject> getAllSubjects() {
        return persist.getAllSubjects();
    }

    public List<UserScoreObject> getTopThree() {
        return persist.getTopThree();
    }

    public List<TutorObject> getAvailableTutors() {
        return persist.getAvailableTutors();
    }

    public List<SubjectObject> getAllSubjectsOrdered() {
        return persist.getAllSubjectsOrdered();
    }

    public SubjectObject loadSubject(int oid) {
        return (SubjectObject) persist.load(SubjectObject.class, oid);
    }

    public List<ConfigObject> getConfig() {
        return persist.getConfig();
    }

    public List<SentMessageObject> getUnreadMessages() {
        return persist.getUnreadMessages();
    }

    public void addNewMessage(UserObject sender, String message, String title) {
        SentMessageObject messageObject = new SentMessageObject();
        messageObject.setSender(sender);
        messageObject.setTitle(title);
        messageObject.setDetails(message);
        messageObject.setSendingDate(new Date());
        messageObject.setMessageRead(false);
        persist.save(messageObject);

    }

    public void markMessageAsRead(SentMessageObject message) {
        message.setMessageRead(true);
        persist.save(message);

    }
}
