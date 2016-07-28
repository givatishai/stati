package com.example.Controllers;

import com.example.Objects.TutorObject;
import com.example.Objects.UserObject;
import com.example.Services.GeneralManager;
import com.example.Services.TutorManager;
import com.example.Services.UserManager;
import com.example.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Sigal on 5/23/2016.
 */

@Controller
public class TutorsController {


    @Autowired
    private GeneralManager generalManager;

    @Autowired
    private Utils utils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TutorManager tutorManager;


    @RequestMapping("/tutors")
    public String tutors(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                model.addAttribute("user", user);
                model.addAttribute("pageName","tutors");
            }

                model.addAttribute("tutors", generalManager.getAvailableTutors());

        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_dashboard";
        } else {
            return "tmpl_tutors";
        }
    }


    @RequestMapping("/tutors-sign-up")
    public void signup(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String signupName, String signuTelephoneNumber, String signupEmail, Integer signupPricePerHour, String signupActivePayment) {
        boolean error = false;
        try {
            if (hasText(signupName) && hasText(signuTelephoneNumber) && hasText(signupEmail)) {
                tutorManager.addTutorObject(signupName, signuTelephoneNumber, signupEmail, signupPricePerHour, true);

            } else {
                error = true;
            }
            utils.setDefaultParameters(request, response, model);
            response.sendRedirect("/tutors");
        } catch (Exception e) {
            error = true;
        }

    }


    @RequestMapping("/tutor-signup-form")
    public String signupForm(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;

        utils.setDefaultParameters(request, response, model);
        return "tmpl_tutor_signup_form";

    }

}