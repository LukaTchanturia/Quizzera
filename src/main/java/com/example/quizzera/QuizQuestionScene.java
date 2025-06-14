package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class QuizQuestionScene implements Initializable {
    @FXML
    private Label quizTitleLabel;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private Button nextButton;
    private final ToggleGroup toggleGroup = new ToggleGroup();

    public void setQuizData() {
        quizTitleLabel.setText(LoadedQuiz.title);
        quizTitleLabel.setStyle("""
                -fx-font-size: 28px;
                    -fx-font-weight: bold;
                    -fx-text-fill: #2d2d2d;""");
        questionNumberLabel.setText("N" + (LoadedQuiz.currentQuestionNumber));
        questionLabel.setText(LoadedQuiz.questions.get(LoadedQuiz.currentQuestionNumber - 1));
        questionLabel.setStyle("-fx-text-fill: #2d2d2d;\n" +
                "    -fx-font-size: 18px;");
    }

    public void setAnswerData() {
        int index = 4 * (LoadedQuiz.currentQuestionNumber - 1);
        answer1.setText(LoadedQuiz.answers.get(index));
        answer2.setText(LoadedQuiz.answers.get(index + 1));
        answer3.setText(LoadedQuiz.answers.get(index + 2));
        answer4.setText(LoadedQuiz.answers.get(index + 3));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answer1.setToggleGroup(toggleGroup);
        answer2.setToggleGroup(toggleGroup);
        answer3.setToggleGroup(toggleGroup);
        answer4.setToggleGroup(toggleGroup);
        setQuizData();
        setAnswerData();
        if (LoadedQuiz.currentQuestionNumber == LoadedQuiz.questions.size()) {
            nextButton.setText("Finish");
        }
    }

    @FXML
    private void nextButtonClicked(ActionEvent event) throws Exception {
        if (!nextButton.getText().equals("Finish")) {
            if (!toggleSelected()) {
                CreateQuizScene.showAlert(Alert.AlertType.ERROR, "Answer title not selected", "Please select an answer title");
            } else {
                String selectedAnswer = getSelectedAnswer();
                if (isCorrectAnswerSelected()) {
                    LoadedUser.currentScoreInLoadedQuiz++;

                }
                showAlert(Alert.AlertType.INFORMATION,"Submitted answer", "Your answer: " + selectedAnswer + "\nCorrect answer: " + LoadedQuiz.correctAnswers.get(LoadedQuiz.currentQuestionNumber-1));

                LoadedQuiz.currentQuestionNumber++;


                SceneSwitcher.switchScene(event, "QuizQuestionScene.fxml");
            }

        } else {
            if (!toggleSelected()) {
                CreateQuizScene.showAlert(Alert.AlertType.ERROR, "Answer title not selected", "Please select an answer title");
            } else {
                String selectedAnswer = getSelectedAnswer();
                if (isCorrectAnswerSelected()) {
                    LoadedUser.currentScoreInLoadedQuiz++;
                    if (gotMaxScore()) {
                        LoadedUser.maxScoreTakenTimes++;
                    }
                    showAlert(Alert.AlertType.INFORMATION,"Submitted answer", "Your answer: " + selectedAnswer + "\nCorrect answer: " + LoadedQuiz.correctAnswers.get(LoadedQuiz.currentQuestionNumber-1));
                }
                LoadedUser.quizzesTaken++;
                CreateQuizScene.showAlert(Alert.AlertType.INFORMATION, "Quiz Completed", "Congratulations! You have completed the quiz successfully \n" +
                        " Your score is " + LoadedUser.currentScoreInLoadedQuiz);
                try (Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
                     PreparedStatement updateUserQuizzesTaken = connection.prepareStatement("UPDATE users SET quizzes_taken = ? WHERE username = ?");
                     PreparedStatement updateUserMaxScoreTakenTimes = connection.prepareStatement("UPDATE users SET max_score_taken_times = ? WHERE username = ?")) {

                    updateUserQuizzesTaken.setInt(1, LoadedUser.quizzesTaken);
                    updateUserQuizzesTaken.setString(2, LoadedUser.username);
                    updateUserQuizzesTaken.executeUpdate();

                    updateUserMaxScoreTakenTimes.setInt(1, LoadedUser.maxScoreTakenTimes);
                    updateUserMaxScoreTakenTimes.setString(2, LoadedUser.username);
                    updateUserMaxScoreTakenTimes.executeUpdate();

                    SceneSwitcher.switchScene(event, "YourStatsScene.fxml");
                    LoadedUser.resetCurrentScoreInLoadedQuiz();
                    LoadedQuiz.resetQuiz();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private boolean toggleSelected() {
        return toggleGroup.getSelectedToggle() != null;
    }

    private String getSelectedAnswer() {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        RadioButton selectedRadio = (RadioButton) selectedToggle;
        return selectedRadio.getText();
    }

    private boolean isCorrectAnswerSelected() {
        return getSelectedAnswer().equals(LoadedQuiz.correctAnswers.get(LoadedQuiz.currentQuestionNumber - 1));
    }

    private boolean gotMaxScore() {
        return LoadedUser.currentScoreInLoadedQuiz == 10;
    }
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

