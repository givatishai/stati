package com.example.Objects;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Sigal on 6/8/2016.
 */
public class ExplanationVideoObject {
    private int oid;
    private String name;
    private UserObject requestingUser;
    private Date requestTime;
    private Date uploadTime;
    private boolean uploaded;
    private SubjectObject subject;
    private String questionText;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserObject getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(UserObject requestingUser) {
        this.requestingUser = requestingUser;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public SubjectObject getSubject() {
        return subject;
    }

    public void setSubject(SubjectObject subject) {
        this.subject = subject;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
