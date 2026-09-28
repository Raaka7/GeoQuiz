package com.example.geoquiz;

public class Question {

    private int questionTextResId;
    private boolean answerTrue;

    public Question(int questionTextResId, boolean answerTrue) {
        this.questionTextResId = questionTextResId;
        this.answerTrue = answerTrue;
    }

    public int getQuestionTextResId() {
        return questionTextResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }
}

