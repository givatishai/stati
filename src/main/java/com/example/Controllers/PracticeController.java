package com.example.Controllers;

import com.example.Objects.*;
import com.example.Services.GeneralManager;
import com.example.Services.GeneratorManager;
import com.example.Services.QuestionManager;
import com.example.Services.UserManager;
import com.example.Services.impl.GeneratorManagerImpl;
import com.example.Utils.QuestionsUtils;
import com.example.Utils.TemplateUtils;
import com.example.Utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

import static com.example.Definitions.USER_TYPE_TEACHER;
import static java.lang.Enum.valueOf;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * Created by Sigal on 5/16/2016.
 */
@Controller
public class PracticeController {

    @Autowired
    private GeneratorManager generatorManager;

    public static final int PATTERN_BASIC = 1;

    @Autowired
    private UserManager userManager;

    @Autowired
    private GeneralManager generalManager;

    @Autowired
    private Utils utils;

    @Autowired
    private QuestionsUtils questionsUtils;

    @Autowired
    private QuestionManager questionManager;


    @RequestMapping("/equation")
    public String equation(HttpServletRequest request, HttpServletResponse response, Model model) {
        final Integer MIN_PARAM = -10;
        final Integer MAX_PARAM = 10;
        EquationObject equationObject = generatorManager.genetareEquation(1, MIN_PARAM, MAX_PARAM, false);
        model.addAttribute("equation", equationObject);
        model.addAttribute("pageName", "practice");
        return "tmpl_equation";
    }

    @RequestMapping("/probability")
    public String probability(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                user.setLastPracticeDate(new Date());
                userManager.updateUser(user);
                final Integer type = 1;
                ProbabilityQuestionObject probabilityQuestionObject = generatorManager.generateProbabilityQuestion(type);
                model.addAttribute("probability", probabilityQuestionObject);
                model.addAttribute("user", user);
                model.addAttribute("subject", 1);
            } else {
                error = true;
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            error = true;
        }
        return "tmpl_probability";
    }


    @RequestMapping("/subjects")
    public String subjects(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);


                List<SubjectObject> subjectObjects = generalManager.getAllSubjectsOrdered();
                model.addAttribute("subjects", subjectObjects);
                model.addAttribute("user", user);
            } else {
                error = true;
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_subjects";
        } else {
            return "tmpl_subjects";
        }
    }

    @RequestMapping("/chech-answer.json")
    public void checkAnswer(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String answer, String userAnswer, String subjectOid) {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                boolean rightAnswer = questionsUtils.isEqualAnswer(answer, userAnswer);
                UserQuestionObject userQuestionObject = new UserQuestionObject();
                userQuestionObject.setSuccess(rightAnswer);
                userQuestionObject.setUser(user);
                userQuestionObject.setSubject(generalManager.loadSubject(Integer.valueOf(subjectOid)));
                questionManager.saveOrUpdateUserQuestionObject(userQuestionObject);
                response.setContentType("application/json; charset=UTF-8");
                JSONObject JObject = new JSONObject();
                JObject.put("rightAnswer", rightAnswer);
                JObject.put("questionIndex", request.getParameter("questionIndex"));
                response.getWriter().print(JObject);
            } else {
                error = true;
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            error = true;
        }
    }

    @RequestMapping("/analyze")
    public String analyze(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model,  int subjectOid) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                model.addAttribute("user", user);
                List<UserQuestionObject> questionObjects = questionManager.getUserQuestionObjectsBySubject(subjectOid,uid);
                AnalyzeObject analyzeObject = new AnalyzeObject(subjectOid, questionObjects);
                model.addAttribute("analyzeObject", analyzeObject);
                model.addAttribute("subject", questionManager.loadSubject(subjectOid));
        } else {
                error = true;
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_analyze";
        } else {
            return "tmpl_analyze";
        }
    }

    @RequestMapping("/request-video.json")
    public void requestVideo(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String description, String question) {
        boolean error = false;
        try {
            ExplanationVideoObject video = new ExplanationVideoObject();
            video.setRequestTime(new Date());
            video.setUploaded(false);
            video.setQuestionText(description);
            video.setSubject(generalManager.loadSubject(1));
            video.setRequestingUser(userManager.loadUser(2));
            questionManager.updateExplanationVideoObject(video);
//            response.setContentType("application/json; charset=UTF-8");
//            JSONObject JObject = new JSONObject();
//            JObject.put("rightAnswer", rightAnswer);
//            JObject.put("questionIndex", request.getParameter("questionIndex"));
//            response.getWriter().print(JObject);
//            int i = 0;
//
            //TODO:
            //Set userObject and subjectObject, use quesiton paramter being passed

        } catch (Exception e) {
            error = true;
        }
    }


}
