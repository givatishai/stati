package com.example.Services;

import com.example.Objects.EquationObject;
import com.example.Objects.ProbabilityQuestionObject;

/**
 * Created by Sigal on 5/20/2016.
 */
public interface GeneratorManager {
    public EquationObject genetareEquation(Integer type, Integer min, Integer max, Boolean allowDecimal);

    public ProbabilityQuestionObject generateProbabilityQuestion(Integer type);
}
