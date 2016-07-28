package com.example.Objects;

import com.example.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sigal on 5/19/2016.
 */
public class ProbabilityQuestionObject extends QuestionObject{
    private int sceanrio;
    private String display;
    private List<String> results;
    private List<String> questions;
    private List<String> answers;
    public final int ARTIFACTS_ROLLS = 1;
    public final int DICE = 0;
    public final int COIN = 2;
    public final int DREIDLE = 3;
    public final int ONE_DIE_ROLLED = 1;
    private int timesRolled;
    private String description;
    private List<List<String>> steps;

    public int getTimesRolled() {
        return timesRolled;
    }

    public void setTimesRolled(int timesRolled) {
        this.timesRolled = timesRolled;
    }

    public ProbabilityQuestionObject (int type) {
        switch (type) {
            case ONE_DIE_ROLLED:
                this.type = ONE_DIE_ROLLED;
                this.timesRolled = Utils.getRandomNumberInRange(1, 4);
                break;
        }
    }

    private List<Integer> getValuesToScore (int numberOfValues) {
        List<Integer> valuesToScore = new ArrayList<Integer>();
        for (int i=0; valuesToScore.size()<numberOfValues; i++) {
            int value = Utils.getRandomNumberInRange(1, 6);
            if (!valuesToScore.contains(value)) {
                valuesToScore.add(value);
            }
        }
        return valuesToScore;
    }

    public int getSceanrio() {
        return sceanrio;
    }

    public void setSceanrio(int sceanrio) {
        this.sceanrio = sceanrio;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> quesionts) {
        this.questions = quesionts;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<String>> getSteps() {
        return steps;
    }

    public void setSteps(List<List<String>> steps) {
        this.steps = steps;
    }
}


