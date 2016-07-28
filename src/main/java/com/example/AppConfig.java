package com.example;

import com.example.Controllers.TutorsController;
import com.example.Services.QuestionManager;
import com.example.Services.TutorManager;
import com.example.Services.impl.GeneratorManagerImpl;
import com.example.Services.impl.QuestionManagetImpl;
import com.example.Services.impl.TutorManagerImpl;
import com.example.Services.impl.UserManagerImpl;
import com.example.Utils.QuestionsUtils;
import com.example.Utils.TemplateUtils;
import com.example.Utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sigal on 5/16/2016.
 */
@Configuration
public class AppConfig {

    @Bean
    public Utils getUtils() {
        return new Utils();
    }

    @Bean
    public UserManagerImpl getUserManager() {
        return new UserManagerImpl();
    }

    @Bean
    public QuestionManagetImpl getQuestionManagetImpl() {
        return new QuestionManagetImpl();
    }

    @Bean
    public QuestionsUtils getQuestionUtils() {
        return new QuestionsUtils();
    }

    @Bean
    public TemplateUtils getTemplateUtils() {
        return new TemplateUtils();
    }

    @Bean
    public TutorManagerImpl getTutorManagerImpl() {
        return new TutorManagerImpl();
    }

    @Bean
    public GeneratorManagerImpl getGenetatorManager() {
        return new GeneratorManagerImpl();
    }

}
