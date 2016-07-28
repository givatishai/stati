package com.example.Utils;

import com.example.Objects.NameObject;
import com.example.Objects.ProbabilityQuestionObject;
import com.example.Objects.QuestionObject;
import com.example.Services.QuestionManager;
import com.example.Utils.Definitions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.Utils.Definitions.*;

/**
 * Created by Sigal on 5/27/2016.
 */
public class QuestionsUtils {

    @Autowired
    private QuestionManager questionManager;

    private int scenarioTypesCount;

    public static List<NameObject> nameObjectList;


    @PostConstruct
    public void init() {
        nameObjectList = questionManager.getAllNameObjects();
    }

    public void generateScenario(QuestionObject questionObject) {
        switch (questionObject.getType()) {
            case ONE_DIE_ROLLED:
                genetareScenarioOneDieRolled((ProbabilityQuestionObject) questionObject);
                int i = 0;
                break;
        }
    }

    private void genetareScenarioOneDieRolled(ProbabilityQuestionObject questionObject) {
        try {
            int scenarioType = Utils.getRandomNumberInRange(1, 2);
            StringBuilder questionDescription = new StringBuilder();
            List<String> questions = new ArrayList<String>();
            List<String> answers = new ArrayList<String>();
            List<List<String>> steps = new ArrayList<List<String>>();
            switch (scenarioType) {
                case SCENARIO_ONE:
                    NameObject nameObject = getRandomNameObject();
                    questionDescription.append(nameObject.getName());
                    questionDescription.append(" has rolled a die.");
                    for (int i = 1; i < 7; i++) {
                        questions.add("What is the probability to get " + i + "?");
                        answers.add("1/6");
                        List<String> questionSteps = new ArrayList<String>();
                        String step1 = "A die has 6 optinal scores: 1, 2, 3, 4, 5, 6.";
                        String step2 = "Every value is shown exactly once on the die.";
                        String step3 = "Thus, every value has a 1/6 probability to score.";
                        String step4 = "In our case, the die shows " + i + " which has the probability of 1/6 to score";
                        questionSteps.add(step1);
                        questionSteps.add(step2);
                        questionSteps.add(step3);
                        questionSteps.add(step4);
                        steps.add(questionSteps);
                    }
                    for (int i = 0; i < 7; i++) {
                        questions.add("What is the probability to get score higher than " + i + "?");
                        answers.add((6 - i) + "/ 6");
                        List<String> questionSteps = new ArrayList<String>();
                        String step1 = "A die has 6 optinal scores: 1, 2, 3, 4, 5, 6.";
                        String step2 = "Every value is shown exactly once on the die.";
                        String step3 = "Thus, every value has a 1/6 probability to score.";
                        List<Integer> numbers = new ArrayList<Integer>();
                        for (int j = i + 1; j < 7; j++) {
                            numbers.add(j);
                        }
                        StringBuilder step4 = new StringBuilder("In our case, we need to find numbers that are higher than " + i + "." + "There are " + numbers.size() + " of these: ");
                        for (int k = 0; k < numbers.size(); k++) {
                            step4.append(numbers.get(k)).append(k == numbers.size() - 1 ? "." : ", ");
                        }
                        String step5 = "Each of them has the probability of 1/6 to score. The probaliity to get one of them would be the sum of them.";
                        StringBuilder step6 = new StringBuilder("Thus, ");
                        for (int k = 0; k < numbers.size(); k++) {
                            step6.append("1/6");
                            if (k != numbers.size() -1) {
                                step6.append("+");
                            }
                        }
                        step6.append("=").append(numbers.size()).append("/6");
                        questionSteps.add(step1);
                        questionSteps.add(step2);
                        questionSteps.add(step3);
                        questionSteps.add(step4.toString());
                        questionSteps.add(step5);
                        questionSteps.add(step6.toString());
                        steps.add(questionSteps);

                    }
                    for (int i = 1; i < 8; i++) {
                        questions.add("What is the probability to get score lower than " + i + "?");
                        answers.add((i - 1) + "/ 6");
                        List<String> questionSteps = new ArrayList<String>();
                        String step1 = "A die has 6 optinal scores: 1, 2, 3, 4, 5, 6.";
                        String step2 = "Every value is shown exactly once on the die.";
                        String step3 = "Thus, every value has a 1/6 probability to score.";
                        List<Integer> numbers = new ArrayList<Integer>();
                        for (int j = i - 1; j > 0 ; j--) {
                            numbers.add(j);
                        }
                        StringBuilder step4 = new StringBuilder("In our case, we need to find numbers that are lower than " + i + "." + "There are " + numbers.size() + " of these: ");
                        for (int k = 0; k < numbers.size(); k++) {
                            step4.append(numbers.get(k)).append(k == numbers.size() - 1 ? "." : ", ");
                        }
                        String step5 = "Each of them has the probability of 1/6 to score. The probaliity to get one of them would be the sum of them.";
                        StringBuilder step6 = new StringBuilder("Thus, ");
                        for (int k = 0; k < numbers.size(); k++) {
                            step6.append("1/6");
                            if (k != numbers.size() -1) {
                                step6.append("+");
                            }
                        }
                        step6.append("=").append(numbers.size()).append("/6");
                        questionSteps.add(step1);
                        questionSteps.add(step2);
                        questionSteps.add(step3);
                        questionSteps.add(step4.toString());
                        questionSteps.add(step5);
                        questionSteps.add(step6.toString());
                        steps.add(questionSteps);
                    }
                    break;
                case SCENARIO_TWO:
                    questionDescription.append("In a lottery game, one die is being rolled. ");
                    questionDescription.append("If it scores more than ");
                    int dieScore = Utils.getRandomNumberInRange(3, 4);
                    questionDescription.append(dieScore);
                    questionDescription.append(", than the player wins. Otherwise the house wins.");
                    questions.add("What is the probability for the player to win?");
                    answers.add((6 - dieScore) + "/6");
                    questions.add("What is the probability for the house to win?");
                    answers.add((dieScore) + "/6");
                    List<String> questionSteps = new ArrayList<String>();
                    List<String> questionSteps2 = new ArrayList<String>();
                    String step1 = "1111111111111111";
                    String step2 = "22222222222222222";
                    String step3 = "33333333333333333";
                    String step4 = "44444444444444444";
                    questionSteps.add(step1);
                    questionSteps.add(step2);
                    questionSteps.add(step3);
                    questionSteps.add(step4);
                    questionSteps2.add(step1);
                    questionSteps2.add(step2);
                    questionSteps2.add(step3);
                    questionSteps2.add(step4);
                    steps.add(questionSteps);
                    steps.add(questionSteps2);
                    break;
            }
            questionObject.setDescription(questionDescription.toString());
            questionObject.setQuestions(questions);
            questionObject.setAnswers(answers);
            questionObject.setSteps(steps);
        } catch (Exception e) {
            int i = 0;
        }

    }

    private NameObject getRandomNameObject() {
        return nameObjectList.get(Utils.getRandomNumberInRange(0, nameObjectList.size() - 1));
    }

    public boolean isEqualAnswer(String answer, String userAnswer) {
        try {
            answer = answer.trim().replace(" ", "");
            userAnswer = userAnswer.trim().replace(" ", "");
            if (answer.trim().equals(userAnswer.trim())) { //regular case
                return true;
            }
            if (answer.contains("/") && userAnswer.contains("/")) { //denumerator case
                int answerNumerator = Integer.valueOf(answer.substring(0, answer.indexOf("/")));
                int userAnswerNumerator = Integer.valueOf(userAnswer.substring(0, userAnswer.indexOf("/")));
                int answerDenumerator = Integer.valueOf(answer.substring(answer.indexOf("/") + 1));
                int userAnswerDenumerator = Integer.valueOf(userAnswer.substring(userAnswer.indexOf("/") + 1));
                if ((answerNumerator / answerDenumerator) == (userAnswerNumerator / userAnswerDenumerator)) {
                    return true;
                }
            }
            if (answer.contains(".") && userAnswer.contains(".")) { //floating poing case
                int answerInteger = Integer.valueOf(answer.substring(0, answer.indexOf(".")));
                int userAnswerInteger = Integer.valueOf(userAnswer.substring(0, userAnswer.indexOf(".")));
                int answerDecimal = Integer.valueOf(answer.substring(answer.indexOf(".") + 1));
                int userAnswerDecimal = Integer.valueOf(userAnswer.substring(userAnswer.indexOf(".") + 1));
                if (answerInteger == userAnswerInteger) {
                    if (answerDecimal == userAnswerDecimal) {
                        return true;
                    } else {
                        String stringAnswerDecimal = String.valueOf(answer);
                        String stringUserAnswerDecimal = String.valueOf(userAnswer);
                        int lengthsDiff = stringAnswerDecimal.length() - stringUserAnswerDecimal.length();
                        if (lengthsDiff != 0) {
                            int i = 0;
                            boolean match = true;
                            while (i < stringAnswerDecimal.length() && i < stringUserAnswerDecimal.length()) {
                                if (stringAnswerDecimal.charAt(i) != stringUserAnswerDecimal.charAt(i)) {
                                    match = false;
                                    break;
                                }
                                i++;
                            }
                            if (match) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


}
