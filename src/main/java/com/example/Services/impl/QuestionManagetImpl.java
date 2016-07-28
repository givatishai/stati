package com.example.Services.impl;

import com.example.Objects.ExplanationVideoObject;
import com.example.Objects.NameObject;
import com.example.Objects.SubjectObject;
import com.example.Objects.UserQuestionObject;
import com.example.Persist;
import com.example.Services.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Sigal on 5/27/2016.
 */
public class QuestionManagetImpl implements QuestionManager {

    @Autowired
    private Persist persist;

    public NameObject getNameObject() {
        return persist.getNameObject();
    }

    public List<NameObject> getMultipleNameObjects(int amount) {
        return persist.getMultipleNameObjects(amount);
    }

    public List<NameObject> getAllNameObjects() {
        return persist.getAllNameObjects();
    }

    public void saveOrUpdateUserQuestionObject(UserQuestionObject userQuestionObject) {
        persist.save(userQuestionObject);
    }

    public List<UserQuestionObject> getUserQuestionObjectsBySubject(int subjectOid, int uid) {
        return persist.getUserQuestionObjectsBySubject(subjectOid, uid);
    }

    public SubjectObject loadSubject(int subjectOid) {
        return (SubjectObject) persist.load(SubjectObject.class, subjectOid);
    }

    public void updateExplanationVideoObject(ExplanationVideoObject video) {
        persist.save(video);
    }

    public long notUplodedExplanationVideo() {
        return persist.notUplodedExplanationVideo();
    }

    public List<ExplanationVideoObject> getNotUplodedExplanationVideo() {
        return persist.getNotUplodedExplanationVideo();
    }

    public List<UserQuestionObject> getUserQuestionObjectByUid(int uid) {
        return persist.getUserQuestionsByUid(uid);
    }

}

