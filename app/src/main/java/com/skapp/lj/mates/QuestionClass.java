package com.skapp.lj.mates;

/**
 * Created by a on 2016-11-08.
 */

class QuestionClass {
    private String questionTitle;
    private String answerLeft;
    private String answerRight;
    private String number;
    private int Color;

    public QuestionClass(String questionTitle, String answerLeft, String answerRight, String number, int Color) {
        this.questionTitle = questionTitle;
        this.answerLeft = answerLeft;
        this.answerRight = answerRight;
        this.number = number;
        this.Color = Color;
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

    public String getNumber() {
        return number+"/5";
    }

    public int getColor() {
        return Color;
    }
}


