package com.fattysmurff.fiddlefaddle.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FiddleFaddle extends Application {

    @FXML
    private Label score;

    @FXML
    private Label level;

    @FXML
    private Label question;

    @FXML
    private RadioButton answerOne;

    @FXML
    private RadioButton answerTwo;

    @FXML
    private RadioButton answerThree;

    @FXML
    private RadioButton answerFour;

    @FXML
    private TitledPane answerTitlePane;

    private Game game = new Game();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/fattysmurff/fiddlefaddle/fiddlefaddle.fxml"));
        Scene scene = new Scene(root, 300, 450);
        primaryStage.setTitle("Fiddle Faddle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize() {
        displayQuestion(game.getQuestion());
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleAboutAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Fiddle Faddle\nCopyright 2017 FattySmurff").showAndWait();
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        game = new Game();
        level.setText(String.format("Level: %s", game.getLevel()));
        updateScore();
        displayQuestion(game.getQuestion());
    }

    @FXML
    private void handleNextAction(ActionEvent event) {
        updateScore();
        displayQuestion(game.getNextQuestion());
    }

    @FXML
    private void handleAnswerOneAction(ActionEvent event) {
        displayResult(game.isCorrectAnswer(0));
    }

    @FXML
    private void handleAnswerTwoAction(ActionEvent event) {
        displayResult(game.isCorrectAnswer(2));
    }

    @FXML
    private void handleAnswerThreeAction(ActionEvent event) {
        displayResult(game.isCorrectAnswer(3));
    }

    @FXML
    private void handleAnswerFourAction(ActionEvent event) {
        displayResult(game.isCorrectAnswer(4));
    }

    private void updateScore() {
        score.setText(String.format("Score: %s / %s", game.getCorrect(), game.getTotal()));
    }

    private void displayResult(boolean result) {
        if (result) {
            answerTitlePane.setText("Correct");
        } else {
            answerTitlePane.setText("Wrong");
        }
        updateScore();
        clearAnswers();
        disableAnswers();
    }

    private void clearAnswers() {
        answerOne.setSelected(false);
        answerTwo.setSelected(false);
        answerThree.setSelected(false);
        answerFour.setSelected(false);
    }

    private void enableAnswers() {
        answerOne.setDisable(false);
        answerTwo.setDisable(false);
        answerThree.setDisable(false);
        answerFour.setDisable(false);
    }

    private void disableAnswers() {
        answerOne.setDisable(true);
        answerTwo.setDisable(true);
        answerThree.setDisable(true);
        answerFour.setDisable(true);
    }

    private void displayQuestion(Question q) {
        question.setText(q.getQuestion());
        answerOne.setText(q.getAnswers().get(0));
        answerTwo.setText(q.getAnswers().get(1));
        answerThree.setText(q.getAnswers().get(2));
        answerFour.setText(q.getAnswers().get(3));
        enableAnswers();
    }
}
