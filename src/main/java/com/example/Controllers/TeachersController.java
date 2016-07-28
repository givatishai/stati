package com.example.Controllers;

import com.example.Objects.AnalyzeObject;
import com.example.Objects.UserObject;
import com.example.Objects.UserQuestionObject;
import com.example.Services.QuestionManager;
import com.example.Services.UserManager;
import com.example.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.example.Definitions.USER_TYPE_TEACHER;

/**
 * Created by Sigal on 6/29/2016.
 */

@Controller
public class TeachersController {

    @Autowired
    private Utils utils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private QuestionManager questionManager;

    @RequestMapping({"/teachers"})
    public String teachers(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                if (user.getUserType() == USER_TYPE_TEACHER) {
                    utils.setDefaultParameters(request, response, model);
                    List<UserObject> monitored = userManager.getMonitoredUsers(uid);
                    model.addAttribute("monitored", monitored);
                    model.addAttribute("pageName", "teachers");

            //    } else {
            //        response.sendRedirect("/dashboard");

                }

           // } else {
          //      response.sendRedirect("/home");
          //      error = true;
           }


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_home";
        } else {
            return "tmpl_teacher";
        }
    }

    @RequestMapping({"/analyze_teachers"})
    public String analyzeStudent(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, int studentUid) {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                if (user.getUserType() == USER_TYPE_TEACHER) {
                    utils.setDefaultParameters(request, response, model);
                    List<UserQuestionObject> questions = questionManager.getUserQuestionObjectByUid(studentUid);
                    AnalyzeObject analyzeObject = new AnalyzeObject(questions);
                    model.addAttribute("analyzeObject", analyzeObject);
                    model.addAttribute("pageName", "analyze_students");

                    //    } else {
                    //        response.sendRedirect("/dashboard");

                }

                // } else {
                //      response.sendRedirect("/home");
                //      error = true;
            }


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_home";
        } else {
            return "tmpl_analyze" ;
        }

    }
    @RequestMapping({"/analyze_students"})
    public String analyzeStudents(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, int studentUid) {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                if (user.getUserType() == USER_TYPE_TEACHER) {
                    utils.setDefaultParameters(request, response, model);
                    List<UserQuestionObject> questions = questionManager.getUserQuestionObjectByUid(studentUid);
                    AnalyzeObject analyzeObject = new AnalyzeObject(questions);
                    model.addAttribute("analyzeObject", analyzeObject);
                    model.addAttribute("pageName", "analyze_students");

                    //    } else {
                    //        response.sendRedirect("/dashboard");

                }

                // } else {
                //      response.sendRedirect("/home");
                //      error = true;
            }


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_home";
        } else {
            return "tmpl_analyze" ;
        }

    }




}

