package com.example.Controllers;

import com.example.Objects.*;
import com.example.Services.GeneralManager;
import com.example.Services.GeneratorManager;
import com.example.Services.QuestionManager;
import com.example.Services.UserManager;
import com.example.Utils.QuestionsUtils;
import com.example.Utils.TemplateUtils;
import com.example.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sigal on 5/24/2016.
 */

@Controller
public class SandboxController {

    @Autowired
    private Utils utils;

    @Autowired
    private GeneralManager generalManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private GeneratorManager generatorManager;

    @Autowired
    private QuestionManager questionManager;

    @Autowired
    private QuestionsUtils questionsUtils;

    @RequestMapping("/sandbox")
    public String sandbox(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            boolean b = questionsUtils.isEqualAnswer(request.getParameter("a"), request.getParameter("b"));
            List<Integer> t = TemplateUtils.selectRandomQuestionsIndexesFromList(10, 5);
            NameObject n = questionManager.getNameObject();
            List<NameObject> n2 = questionManager.getMultipleNameObjects(2);
            boolean error = false;
            final Integer MIN_PARAM = -10;
            final Integer MAX_PARAM = 10;
            List<UserQuestionObject> q = userManager.getUserQuestionsByUid(1);
            UserObject user = userManager.loadUser(1);
            List<SubjectObject> subjectObjects = generalManager.getAllSubjects();
            EquationObject equationObject = generatorManager.genetareEquation(1, MIN_PARAM, MAX_PARAM, false);
            model.addAttribute("activeSubject", user.getCurrentSubject());
            int currentSubjectIndex = -1;
            for (int i = 0; i < subjectObjects.size(); i++) {
                if (subjectObjects.get(i).getOid() == (user.getCurrentSubject().getOid())) {
                    currentSubjectIndex = i;
                    break;
                }
            }

            model.addAttribute("beforeSubject", subjectObjects.get(currentSubjectIndex - 1));
            model.addAttribute("afterSubject", subjectObjects.get(currentSubjectIndex + 1));
            model.addAttribute("subjects", subjectObjects);
            model.addAttribute("equation", equationObject);
            model.addAttribute("pageName", "practice");
            utils.setDefaultParameters(request, response, model);

        } catch (Exception e) {
            int i = 0;
        }
        return "tmpl_sandbox";
    }

    @RequestMapping("/sandbox2")
    public String sandbox2(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserObject user = userManager.loadUser(1);
        List<SubjectObject> subjectObjects = generalManager.getAllSubjects();
        model.addAttribute("subjects", subjectObjects);
        utils.setDefaultParameters(request, response, model);
        ProbabilityQuestionObject probabilityQuestionObject = generatorManager.generateProbabilityQuestion(1);
        model.addAttribute("probability", probabilityQuestionObject);
        List<String> list = new ArrayList<String>();
        list.add("Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 Step 1 ");
        list.add("Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 Step 2 ");
        list.add("Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 Step 3 ");
//        list.add("Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 Step 4 ");
        model.addAttribute("list", list);
        return "tmpl_sandbox2";
    }

    @RequestMapping("/changeMonitor.json")
    public void sigal(HttpServletRequest request, HttpServletResponse response, Model model) {
        Date d = new Date();
    }





}