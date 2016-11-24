package com.skapp.lj.mates;

/**
 * Created by a on 2016-11-08.
 */

class QuestionClass {
    private String questionTitle;
    private String answerLeft;
    private String answerRight;

    public QuestionClass(String questionTitle, String answerLeft, String answerRight)
    {
        this.questionTitle = questionTitle;
        this.answerLeft = answerLeft;
        this.answerRight = answerRight;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getAnswerLeft() {
        return answerLeft;
    }

    public String getAnswerRight() {
        return answerRight;
    }
}


