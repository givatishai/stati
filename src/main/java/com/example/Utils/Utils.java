package com.example.Utils;

import com.example.Objects.ConfigObject;
import com.example.Services.GeneralManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sigal on 5/16/2016.
 */
public class Utils {

    @Autowired
    private TemplateUtils templateUtils;

    @Autowired
    private GeneralManager generalManager;

    private static List<ConfigObject> config = null;

    @PostConstruct
    private void initialize(){
        config = generalManager.getConfig();
    }

    public void setDefaultParameters(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("siteName", getConfigValueByKey("site_name"));
        model.addAttribute("templateUtils", templateUtils);
    }

    public static int getRandomNumberInRange(int min, int max) {
        return getRandomNumberInRange(min, max, new ArrayList<Integer>());
    }

    public static int getRandomNumberInRange(int min, int max, List<Integer> dissaproved) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Integer num = null;
        Random r = new Random();
        do {
            num = r.nextInt((max - min) + 1) + min;
        } while (dissaproved.contains(num));

        return num;

    }

    public String getConfigValueByKey(String key){
        for (ConfigObject con: config) {
            if(con.getConfigKey().equals(key)){
                return con.getConfigValue();
            }
        }
        return  null;
    }


}
