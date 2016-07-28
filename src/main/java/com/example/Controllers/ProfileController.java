package com.example.Controllers;

import com.example.Objects.UserObject;
import com.example.Services.GeneralManager;
import com.example.Services.UserManager;
import com.example.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sigal on 5/28/2016.
 */

@Controller
public class ProfileController {

    @Autowired
    private Utils utils;

    @Autowired
    private GeneralManager generalManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping("/profile")
    public String userprofile(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            utils.setDefaultParameters(request, response, model);
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                model.addAttribute("user", user);
                model.addAttribute("pageName", "dashboard");
                model.addAttribute("top3", generalManager.getTopThree());
            } else {
                response.sendRedirect("/home");
                error = true;
            }

        }
         catch (Exception e) {
        error = true;
    }

    if (error) {
        return "tmpl_dashboard";
    } else {
        return "tmpl_profile";
    }

    }
}
