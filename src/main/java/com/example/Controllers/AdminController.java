package com.example.Controllers;

import com.example.Objects.UserObject;
import com.example.Services.GeneralManager;
import com.example.Services.QuestionManager;
import com.example.Services.UserManager;
import com.example.Utils.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.example.Definitions.USER_TYPE_ADMIN;
import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Sigal on 6/12/2016.
 */

@Controller
public class AdminController {

    @Autowired
    private Utils utils;

    @Autowired
    private UserManager userManager;

    @Autowired
    private QuestionManager questionManager;

    @Autowired
    private GeneralManager generalManager;

    @RequestMapping({"/admin"})
    public String admin(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            //    if (uid != null) {
            UserObject user = userManager.loadUser(uid);
            //          if(user.getUserType()==USER_TYPE_ADMIN) {

            utils.setDefaultParameters(request, response, model);
            long countUsers = userManager.countUsers();
            List<UserObject> pendingUsers = userManager.getAllPendingUsers();
            long countNotUploadedVideos = questionManager.notUplodedExplanationVideo();
            List<UserObject> supervisors = userManager.getAllSupervisors();

            model.addAttribute("user", user);
            model.addAttribute("countUsers", countUsers);
            model.addAttribute("countNotUploadedVideos", countNotUploadedVideos);
            model.addAttribute("pendingVideos", questionManager.getNotUplodedExplanationVideo());
            model.addAttribute("regularUsers", userManager.getAllRegularUsers());
            model.addAttribute("countRegularUsers", userManager.getAllRegularUsers().size());
            model.addAttribute("supervisors", supervisors);
            model.addAttribute("pendingUsers",pendingUsers);
            model.addAttribute("countPendingUsers",pendingUsers.size());
            model.addAttribute("unreadMessages",generalManager.getUnreadMessages());
            model.addAttribute("countUnreadMessages",generalManager.getUnreadMessages().size());
            model.addAttribute("pageName", "admin");
            //}
            //   else {
            //     response.sendRedirect("/dashboard");
;
//                }

            // } else {
            //   response.sendRedirect("/home");
            // error = true;
            //}


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_home";
        } else {
            return "tmpl_admin";
        }
    }


    @RequestMapping("/change_supervisor.json")
    public void changeSupervisor(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String supervisor, String studentUid) throws Exception {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                UserObject student = userManager.loadUser(Integer.valueOf(studentUid));
                UserObject sup = userManager.loadUser(Integer.valueOf(supervisor));
                student.setSupervisor(sup);
                userManager.updateUser(student);
            }
        } catch (Exception e) {
            error = true;
        }
        response.setContentType("application/json; charset=UTF-8");
        JSONObject JObject = new JSONObject();
        JObject.put("error", error);
        response.getWriter().print(JObject);

    }

    @RequestMapping("/set_active.json")
    public void setActive(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String pendingUserUid) throws Exception {
        boolean error = false;
        try {
            if (uid != null) {
                UserObject user = userManager.loadUser(uid);
                UserObject pendingUser = userManager.loadUser(Integer.valueOf(pendingUserUid));
                pendingUser.setActive(true);
                userManager.updateUser(pendingUser);
            }
        } catch (Exception e) {
            error = true;
        }
        response.setContentType("application/json; charset=UTF-8");
        JSONObject JObject = new JSONObject();
        JObject.put("error", error);
        JObject.put("uid",pendingUserUid);
        response.getWriter().print(JObject);

    }


    @RequestMapping({"/contact_us"})
    public String contactUs(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean error = false;
        try {
            //    if (uid != null) {
            UserObject user = userManager.loadUser(uid);
            utils.setDefaultParameters(request, response, model);
            model.addAttribute("user", user);
            model.addAttribute("pageName", "contact_us");

            // } else {
            //   response.sendRedirect("/home");
            // error = true;
            //}


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_dashboard";
        } else {
            return "tmpl_contact_us";
        }
    }


    @RequestMapping({"/message_sent"})
    public String messageSent(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String title, String message) {
        boolean error = false;
        try {
            //    if (uid != null) {
            UserObject user = userManager.loadUser(uid);
            utils.setDefaultParameters(request, response, model);
            model.addAttribute("user", user);

            generalManager.addNewMessage(user,message,title);

            // } else {
            //   response.sendRedirect("/home");
            // error = true;
            //}


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_dashboard";
        } else {
            return "tmpl_contact_us";
        }
    }

    @RequestMapping({"/mark_as_read"})
    public String markAsR(@CookieValue("uid") Integer uid, HttpServletRequest request, HttpServletResponse response, Model model, String messageId) {
        boolean error = false;
        try {
            //    if (uid != null) {
            UserObject user = userManager.loadUser(uid);
            utils.setDefaultParameters(request, response, model);
            model.addAttribute("user", user);



            // } else {
            //   response.sendRedirect("/home");
            // error = true;
            //}


        } catch (Exception e) {
            error = true;
        }
        if (error) {
            return "tmpl_dashboard";
        } else {
            return "tmpl_contact_us";
        }
    }




}
