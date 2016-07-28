package com.example.Services;

import com.example.Objects.ExplanationVideoObject;
import com.example.Objects.NameObject;
import com.example.Objects.SubjectObject;
import com.example.Objects.UserQuestionObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sigal on 5/27/2016.
 */

@Transactional
public interface QuestionManager {

    public NameObject getNameObject();

    public List<NameObject> getMultipleNameObjects(int amount);

    public List<NameObject> getAllNameObjects();

    public void saveOrUpdateUserQuestionObject(UserQuestionObject userQuestionObject);

    public List<UserQuestionObject> getUserQuestionObjectsBySubject(int subjectOid, int uid);

    public SubjectObject loadSubject(int subjectOid);

    public void updateExplanationVideoObject(ExplanationVideoObject video);

    public long notUplodedExplanationVideo();

    public List<ExplanationVideoObject> getNotUplodedExplanationVideo();

    public List<UserQuestionObject> getUserQuestionObjectByUid(int uid);

    }