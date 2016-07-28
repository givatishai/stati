package com.example.Objects;

import com.example.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sigal on 5/16/2016.
 */
public class EquationObject extends QuestionObject{
    private List<Float> coeficients;
    private List<Float> constants;
    private List<Float> variables;
    private List<String> variableChars;
    private List<String> usedVariableChars;
    private Integer type;
    private String display;
    private String resultDisplay;


    public EquationObject (Integer type, Integer min, Integer max, Boolean allowDecimal) {
        try {
            variableChars = new ArrayList<String>();
            variableChars.add("X");
            Random rand = new Random();
            switch (type){ //aX+b=c
                case 1:
                    String variableChar = variableChars.get((variableChars.size()) == 1 ? 0 : Utils.getRandomNumberInRange(0,variableChars.size()));
                    usedVariableChars = new ArrayList<String>();
                    usedVariableChars.add(variableChar);
                    List<Integer> ignoreValues = new ArrayList<>();
                    ignoreValues.add(0);
                    Utils.getRandomNumberInRange(min, max, ignoreValues);
                    Integer coeficient = Utils.getRandomNumberInRange(min, max, ignoreValues);
                    Integer constant = Utils.getRandomNumberInRange(min, max, ignoreValues);
                    Integer variable = Utils.getRandomNumberInRange(min, max, ignoreValues);
                    Integer result = (coeficient * variable + constant);
                    List<Float> coeficients = new ArrayList<Float>();
                    List<Float> constants = new ArrayList<Float>();
                    List<Float> variables = new ArrayList<Float>();
                    coeficients.add(Float.valueOf(coeficient));
                    constants.add(Float.valueOf(constant));
                    constants.add(Float.valueOf(result));
                    variables.add(Float.valueOf(variable));
                    this.coeficients = coeficients;
                    this.constants = constants;
                    this.variables = variables;
                    this.type = type;
                    createEquationByPattern();
                    removeLeadingOnes();
            }
        } catch (Exception e) {
            e.toString();
        }

    }

    public List<Float> getCoeficients() {
        return coeficients;
    }

    public void setCoeficients(List<Float> coeficients) {
        this.coeficients = coeficients;
    }

    public List<Float> getConstants() {
        return constants;
    }

    public void setConstants(List<Float> constants) {
        this.constants = constants;
    }

    public List<Float> getVariables() {
        return variables;
    }

    public void setVariables(List<Float> variables) {
        this.variables = variables;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getResultDisplay() {
        return resultDisplay;
    }

    public void setResultDisplay(String resultDisplay) {
        this.resultDisplay = resultDisplay;
    }

    public void createEquationByPattern() {
        String pattern = "";
        switch (this.type){
            case 1:
                StringBuilder b = new StringBuilder();
                b.append(getValueAsString(this.coeficients.get(0)));
                b.append(usedVariableChars.get(0));
                b.append(this.constants.get(0) > 0 ? "+" : "").append(getValueAsString(this.constants.get(0)));
                b.append("=");
                b.append(getValueAsString(this.constants.get(1)));
                pattern = b.toString();
        }
        this.display = pattern;
        this.resultDisplay = usedVariableChars.get(0) + "=" + getValueAsString(this.variables.get(0));
    }

    private boolean isIntegerValue(float num){
        return num == Math.round(num);
    }

    private String getValueAsString(float num) {
        if (isIntegerValue(num)) {
            return String.valueOf(Math.round(num));
        }
        return String.valueOf(num);
    }

    private void removeLeadingOnes() {
        for (String var : this.variableChars) {
            this.display = this.display.replace("+1" + var, "+" + var);
            this.display = this.display.replace("-1" + var, "-" + var);
            if (this.display.indexOf("1" + var) == 0) {
                this.display = this.display.substring(1);
            }
        }
    }


}
