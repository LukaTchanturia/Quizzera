package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateQuizScene implements Initializable {
    @FXML
    private TextField quizTitleField;
    @FXML
    private VBox questionsVbox;

    private List<QuestionNode> questionNodes = new ArrayList<>();



    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "HomePageScene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 10; i++) {
            QuestionNode node = new QuestionNode(i + 1);
            questionNodes.add(node);
            questionsVbox.getChildren().add(node);
        }
    }

    @FXML
    private void submitButtonClicked(ActionEvent event) {
        String title = quizTitleField.getText();
        if (title.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Quiz title is required.");
            return;
        }
        for (int i = 0; i < questionNodes.size(); i++) {
            if (!questionNodes.get(i).isValid()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Question " + (i + 1) + " is incomplete or invalid.");
                return;
            }
        }

        try (Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password)) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement quizStatement = connection.prepareStatement(
                            "INSERT INTO quizzes (title, created_by) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement questionStatement = connection.prepareStatement(
                            "INSERT INTO questions (question_text, quiz_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement answerStatement = connection.prepareStatement(
                            "INSERT INTO answers (answer_text, question_id, is_correct) VALUES (?, ?, ?)");
            ) {
                // Insert quiz
                quizStatement.setString(1, title);
                quizStatement.setString(2, LoadedUser.username);
                quizStatement.executeUpdate();

                try (ResultSet quizKeys = quizStatement.getGeneratedKeys()) {
                    if (!quizKeys.next()) {
                        connection.rollback();
                        showAlert(Alert.AlertType.ERROR, "Fail", "Failed to create quiz.");
                        return;
                    }
                    int quizId = quizKeys.getInt(1);

                    // Insert questions and answers
                    for (QuestionNode questionNode : questionNodes) {
                        questionStatement.setString(1, questionNode.getQuestionText());
                        questionStatement.setInt(2, quizId);
                        questionStatement.executeUpdate();

                        try (ResultSet questionKeys = questionStatement.getGeneratedKeys()) {
                            if (!questionKeys.next()) {
                                connection.rollback();
                                showAlert(Alert.AlertType.ERROR, "Fail", "Failed to create question.");
                                return;
                            }
                            int questionId = questionKeys.getInt(1);

                            List<String> answers = List.of(questionNode.getAnswers());
                            int correctAnswerIndex = questionNode.getCorrectAnswerIndex();
                            for (int i = 0; i < answers.size(); i++) {
                                answerStatement.setString(1, answers.get(i));
                                answerStatement.setInt(2, questionId);
                                answerStatement.setBoolean(3, i == correctAnswerIndex);
                                answerStatement.executeUpdate();
                            }
                        }
                    }
                    connection.commit();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Quiz created successfully!");
                    SceneSwitcher.switchScene(event, "HomePageScene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error occurred while saving the quiz.");
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Connection Error", "Failed to connect to the database.");
        }
    }


    public static void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
