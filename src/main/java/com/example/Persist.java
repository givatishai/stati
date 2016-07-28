package com.example;

import com.example.Objects.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Definitions.USER_TYPE_ADMIN;
import static com.example.Definitions.USER_TYPE_TEACHER;

/**
 * Created by Sigal on 5/20/2016.
 */
@Service
@Component
public class Persist {

    private SessionFactory sessionFactory;

    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public Session getQuerySession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public Object load(Class clazz, int id) {
        return getQuerySession().get(clazz, id);
    }

    public Object load(Class clazz, long id) {
        return getQuerySession().get(clazz, id);
    }


    public UserObject getUserByEmailAndPassword(String email, String passowrd) {
        StringBuilder query = new StringBuilder();
        query.append("FROM UserObject WHERE userName=");
        query.append("'").append(email).append("'");
        query.append(" AND password=");
        query.append("'").append(passowrd).append("'");
        return (UserObject) getQuerySession().createQuery(query.toString()).uniqueResult();
    }

    public List<SubjectObject> getAllSubjects() {
        return (List<SubjectObject>) getQuerySession().createQuery("FROM SubjectObject").list();
    }

    public List<UserScoreObject> getTopThree() {
        return (List<UserScoreObject>) getQuerySession().createQuery("SELECT u FROM UserScoreObject u ORDER BY score DESC").setMaxResults(3).list();
    }

    public List<TutorObject> getAvailableTutors() {
        return (List<TutorObject>) getQuerySession().createQuery("FROM TutorObject WHERE active_payment=1").list();
    }

    public List<UserQuestionObject> getUserQuestionsByUid(int uid) {
        StringBuilder query = new StringBuilder("FROM UserQuestionObject ");
        query.append(" WHERE uid=");
        query.append(uid);
        return (List<UserQuestionObject>) getQuerySession().createQuery(query.toString()).list();
    }

    public List<SubjectObject> getAllSubjectsOrdered() {
        return (List<SubjectObject>) getQuerySession().createQuery("FROM SubjectObject ORDER BY priority ASC").list();
    }

    public NameObject getNameObject() {
        return (NameObject) getQuerySession().createQuery("FROM NameObject ORDER BY RAND()").setMaxResults(1).uniqueResult();
    }

    public List<NameObject> getMultipleNameObjects(int amount) {
        return (List<NameObject>) getQuerySession().createQuery("FROM NameObject ORDER BY RAND()").setMaxResults(amount).list();
    }

    public List<NameObject> getAllNameObjects() {
        return (List<NameObject>) getQuerySession().createQuery("FROM NameObject").list();
    }

    public List<UserQuestionObject> getUserQuestionObjectsBySubject(int subjectOid, int uid) {
        StringBuilder query = new StringBuilder("FROM UserQuestionObject ");
        query.append(" WHERE subject.oid=");
        query.append(subjectOid);
        query.append(" AND uid=");
        query.append(uid);
        return (List<UserQuestionObject>) getQuerySession().createQuery(query.toString()).list();
    }

    //   public List<TutorObject> addTutor() {
    //     return (List<TutorObject>) getQuerySession().createQuery("INSERT INTO tutors").list();
//    }

    public long countUsers() {
        return (long) getQuerySession().createQuery("SELECT COUNT(*) FROM UserObject").uniqueResult();
    }

    public long notUplodedExplanationVideo() {
        return (long) getQuerySession().createQuery("SELECT COUNT(*) FROM ExplanationVideoObject WHERE uploaded=FALSE").uniqueResult();
    }


    public List<ExplanationVideoObject> getNotUplodedExplanationVideo() {
        return (List<ExplanationVideoObject>) getQuerySession().createQuery("FROM ExplanationVideoObject WHERE uploaded=FALSE ORDER BY upload_time DESC").list();
    }

    public List<ConfigObject> getConfig() {
        return (List<ConfigObject>) getQuerySession().createQuery("FROM ConfigObject").list();
    }

    public List<UserObject> getMonitoredUsers(int uid) {
        StringBuilder query = new StringBuilder("FROM UserObject WHERE supervisor.uid=");
        query.append(uid);
        return (List<UserObject>) getQuerySession().createQuery(query.toString()).list();
    }

    public List<UserObject> getAllRegularUsers() {
       return (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE userType!=" + USER_TYPE_ADMIN).list();
    }


    //public List<UserObject> getAllStudents(){
    //    return(List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE ")
   // }

    public UserObject changeSupervisor(int uid,int supervisor){
        return (UserObject) getQuerySession().createQuery("UPDATE UserObject SET supervisor="+supervisor+ "WHERE uid="+uid ).uniqueResult();
    }


    public List<UserQuestionObject> getUserQuestionObjectByUid(int uid){
        return (List<UserQuestionObject>) getQuerySession().createQuery("FROM UserQuestionObject WHERE uid=" + uid);//list??
    }

    public List<UserObject> getAllSupervisors(){
        return (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE userType IN("+USER_TYPE_ADMIN+","+USER_TYPE_TEACHER+")").list();
    }

    public List<UserObject> getAllPendingUsers(){
        return  (List<UserObject>) getQuerySession().createQuery("FROM UserObject WHERE active=FALSE").list();
    }
    public List<SentMessageObject> getUnreadMessages(){
        return (List<SentMessageObject>) getQuerySession().createQuery("FROM SentMessageObject WHERE messageRead=FALSE").list();
    }
}
