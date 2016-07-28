package com.example.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Sigal on 5/18/2016.
 */
public class TemplateUtils {
    public static List<Integer> selectRandomQuestionsIndexesFromList(int listSize, int numberOfQuestions) {
        if(numberOfQuestions > listSize) {
            numberOfQuestions = listSize;
        }
        List<Integer> questions = new ArrayList<Integer>();
        for (int i=0; i<listSize; i++) {
            questions.add(i);
        }
        List<Integer> indexes = new ArrayList<Integer>();
        for(int i=0; i<numberOfQuestions; i++) {
            Collections.shuffle(questions, new Random(System.nanoTime()));
            indexes.add(questions.get(0));
            questions.remove(0);
        }
        return indexes;
    }

    public static int test() {
        return -1;
    }

}
