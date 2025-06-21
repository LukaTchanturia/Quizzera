package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class QuizScene implements Initializable {
    @FXML
    private Label quizTitleLabel;
    @FXML
    private void buttonClicked(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene(event,"QuizQuestionScene.fxml");
    }


    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        if(LoadedQuiz.created_by.equals("admin")){
            SceneSwitcher.switchScene(event,"AdminQuizScene.fxml");

        }
        else {
            SceneSwitcher.switchScene(event,"CommunityQuizScene.fxml");
        }
        LoadedQuiz.resetQuiz();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void setQuizData(int quizId,String quizTitle) {

        LoadedQuiz.id = quizId;
        LoadedQuiz.title = quizTitle;
        LoadedQuiz.currentQuestionNumber = 1;
        LoadedQuiz.created_by = getQuizCreatedBy();
        quizTitleLabel.setText(quizTitle + " by " + LoadedQuiz.created_by);
        try (Connection connection = DriverManager.getConnection(MainAPP.url,MainAPP.username,MainAPP.password)){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?");
            preparedStatement.setInt(1,quizId);
            ResultSet questionsRS = preparedStatement.executeQuery();
            while (questionsRS.next()){
                LoadedQuiz.questions.add(questionsRS.getString("question_text"));
                int questionId = questionsRS.getInt("id");
                try (PreparedStatement questionStatement = connection.prepareStatement("SELECT * FROM answers WHERE question_id = ?")){
                    questionStatement.setInt(1,questionId);
                    ResultSet answersRS = questionStatement.executeQuery();
                    while (answersRS.next()){
                        boolean isCorrect = answersRS.getBoolean("is_correct");
                        if (isCorrect)
                            LoadedQuiz.correctAnswers.add(answersRS.getString("answer_text"));
                        LoadedQuiz.answers.add(answersRS.getString("answer_text"));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getQuizCreatedBy(){
        try (Connection connection = DriverManager.getConnection(MainAPP.url,MainAPP.username,MainAPP.password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT created_by FROM quizzes WHERE id = ?")){
            preparedStatement.setInt(1,LoadedQuiz.id);
            ResultSet createdByRS = preparedStatement.executeQuery();
            if (createdByRS.next()){
                return createdByRS.getString("created_by");
            }
            else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
