package com.fattysmurff.fiddlefaddle.javafx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private List<Question> questions = new ArrayList<>();
    private int total = 1;
    private int correct = 0;
    private int level = 1;
    private int currentIndex = 0;


    public Game() {

        Question q1 = new Question();
        q1.setQuestion(
                "What is the job title of the person in charge of the camera and lighting crews working on a film?");
        q1.setAnswers(Arrays.asList("Production Manager", "Cinematographer", "Unit Manager", "Gaffer"));
        q1.setCorrectAnswerIndex(1);
        questions.add(q1);

        Question q2 = new Question();
        q2.setQuestion("What is a baby rabbit called?");
        q2.setAnswers(Arrays.asList("Kitten", "Pup", "Rabee", "Tuber"));
        q2.setCorrectAnswerIndex(0);
        questions.add(q2);

        Question q3 = new Question();
        q3.setQuestion("In January 1996 Bill Clinton challenged congress to \"Never, ever\" do this again");
        q3.setAnswers(Arrays.asList("Kiss Monica", "Kiss Hilary", "Shutdown government", "Run for re-election"));
        q3.setCorrectAnswerIndex(2);
        questions.add(q3);

        Question q4 = new Question();
        q4.setQuestion("This Mississippi capital was named for a general who later became president?");
        q4.setAnswers(Arrays.asList("Topeka", "Jackson", "Richmond", "Grant"));
        q4.setCorrectAnswerIndex(2);
        questions.add(q4);

        Question q5 = new Question();
        q5.setQuestion("This Honda minivan has a Homeric name");
        q5.setAnswers(Arrays.asList("Production Manager", "Cinematographer", "Unit Manager", "Gaffer"));
        q5.setCorrectAnswerIndex(3);
        questions.add(q5);
    }

    public Question getQuestion() {
        return questions.get(currentIndex);
    }

    public boolean isCorrectAnswer(int value) {
        if (value == getQuestion().getCorrectAnswerIndex()) {
            correct++;
            return true;
        } else {
            return false;
        }
    }

    public Question getNextQuestion() {
        total++;
        if (questions.size() <= ++currentIndex) {
            currentIndex = 0;
        }

        return getQuestion();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
