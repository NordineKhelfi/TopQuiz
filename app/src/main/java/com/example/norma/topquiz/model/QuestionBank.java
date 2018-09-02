package com.example.norma.topquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by norma on 26/10/2017.
 */

public class QuestionBank {

    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        // Shuffle the question list before storing it
        Collections.shuffle(questionList);
        mQuestionList = questionList;
        mNextQuestionIndex = 0;
    }

    public Question getQuestion() {
        // Loop over the questions and return a new one at each call

        // Ensure we loop over the questions
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return mQuestionList.get(mNextQuestionIndex++);

    }

    public int getNumberOfQuestions(){
        return mQuestionList.size();
    }

}
