package com.example.Objects;

/**
 * Created by Sigal on 5/25/2016.
 */
public class UserQuestionObject {
    private int oid;
    private UserObject user;
    private SubjectObject subject;
    private int level;
    private boolean success;
    private int questionType;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
    }

    public SubjectObject getSubject() {
        return subject;
    }

    public void setSubject(SubjectObject subject) {
        this.subject = subject;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
