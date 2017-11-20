package com.android.ruangguru.feature.games.model;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameAnswerChoice {

    private char option;
    private String question;
    private Boolean isAnswer;
    private int type;
    private Boolean selected;

    public GameAnswerChoice(char option, String question, Boolean isAnswer, int type, Boolean selected) {
        this.option = option;
        this.question = question;
        this.isAnswer = isAnswer;
        this.type = type;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }
}
