package com.example.Services.impl;

import com.example.Objects.EquationObject;
import com.example.Objects.ProbabilityQuestionObject;
import com.example.Services.GeneratorManager;
import com.example.Services.QuestionManager;
import com.example.Utils.QuestionsUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Sigal on 5/16/2016.
 */
public class GeneratorManagerImpl implements GeneratorManager{

    @Autowired
    private QuestionsUtils questionsUtils;

    public EquationObject genetareEquation(Integer type, Integer min, Integer max, Boolean allowDecimal) {
        return new EquationObject(type, min, max, allowDecimal);
    }

    public ProbabilityQuestionObject generateProbabilityQuestion(Integer type) {
        ProbabilityQuestionObject questionObject = new ProbabilityQuestionObject(type);
        questionsUtils.generateScenario(questionObject);
        return questionObject;
    }
}
