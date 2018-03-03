package com.fattysmurff.fiddlefaddle.javafx;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int correctAnswerIndex = 0;
    private String Question = "";
    private List<String> answers = new ArrayList<>();

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
