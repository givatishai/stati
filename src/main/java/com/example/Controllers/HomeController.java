package com.example.Controllers;

import com.example.Objects.UserObject;
import com.example.Services.UserManager;
import com.example.Services.impl.UserManagerImpl;
import com.example.Utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.Definitions.USER_TYPE_BASIC;
import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Sigal on 5/16/2016.
 */
@Controller
public class HomeController {

    @Autowired
    private Utils utils;

    @Autowired
    private UserManager userManager;

    @RequestMapping({"/", "/home"})
    public String showHomePage(HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            Cookie[] cookies = request.getCookies();
            Integer uid = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uid")) {
                    uid = Integer.valueOf(cookie.getName());
                }
            }
            if (uid != null) {
                response.sendRedirect("/dashboard");
            } else {
                utils.setDefaultParameters(request, response, model);
            }
        } catch (Exception e) {
            error = true;
        }
        return "tmpl_home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, String loginUserName, String loginPassword, boolean admin) {
        boolean error = false;
        utils.setDefaultParameters(request, response, model);
        try {
            UserObject user = userManager.getUserByEmailAndPassword(loginUserName, loginPassword);
            if (user != null) {
                response.addCookie(new Cookie("uid", String.valueOf(user.getUid())));
                request.getSession().setAttribute("uid", user.getUid());
                if (!admin) {
                    user.setLastLogin(new Date());
                    userManager.updateUser(user);
                }
                if (user.isActive()) {
                    response.sendRedirect("/dashboard");
                } else {
                    error = true;
                    model.addAttribute("errorMessage", "Your Signup Request Is Waiting For Approval");
                }
            } else {
                error = true;
                model.addAttribute("errorMessage", "Wrong Credentials");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "General Error");
            error = true;
        }
        if (error) {
            model.addAttribute("error", true);
        }
        return "tmpl_home";
    }

    @RequestMapping("/signup")
    public String signup(HttpServletRequest request, HttpServletResponse response, Model model, String signupName, String signupUserName, String signupPassword, String signupRepeatPassword) {
        boolean error = false;
        try {
            if (hasText(signupName) && hasText(signupUserName) && hasText(signupPassword) && hasText(signupRepeatPassword)) {
                if (signupPassword.equals(signupRepeatPassword)) {
                    userManager.addNewUser(signupName, signupUserName, signupPassword, USER_TYPE_BASIC);

                } else {
                    error = true;
                }
            } else {
                error = true;
            }
            utils.setDefaultParameters(request, response, model);
        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_home";
        } else {
            return "tmpl_signup_response";
        }
    }

    @RequestMapping("/logout")
    public String logou(HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            response.addCookie(new Cookie("uid", null));
        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_error";
        }
        return "tmpl_home";
    }
}
