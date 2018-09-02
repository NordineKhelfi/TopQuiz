package com.example.norma.topquiz.model;

import java.util.List;

/**
 * Created by norma on 26/10/2017.
 */

public class Question {

    private String sQuestion;
    private List<String> lChoiceList;
    private int iAnswerIndex;
    private String correctAnswer;


    public Question(String sQuestion, List<String> lChoiceList, int iAnswerIndex, String correctAnswer) {
        this.sQuestion = sQuestion;
        this.lChoiceList = lChoiceList;
        this.iAnswerIndex = iAnswerIndex;
        this.correctAnswer = correctAnswer;
    }


    public String getQuestion() {
        return sQuestion;
    }

    public List<String> getChoiceList() {
        return lChoiceList;
    }

    public int getAnswerIndex() {
        return iAnswerIndex;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
