package com.example.quizzera;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class AIQuizScene {
    @FXML
    private TextField topicField;

    @FXML
    private void generateAIQuizClicked(ActionEvent event) {
        String topic = topicField.getText().trim();
        if (topic.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing topic", "Please enter a topic.");
            return;
        }

        new Thread(() -> {
            try {
                String prompt = "Create a JSON array of 10 multiple-choice questions about \"" + topic +
                        "\". Each should have 4 answer choices and clearly show the correct answer like:\n" +
                        "[{\"question\":\"...\",\"answers\":[\"A\",\"B\",\"C\",\"D\"],\"correct\":\"B\"},...]";

                String responseJson = GeminiService.getAIQuiz(prompt);

                JSONArray jsonArray = new JSONArray(responseJson);

                LoadedQuiz.resetQuiz();
                LoadedQuiz.title = "AI Quiz: " + topic;
                LoadedQuiz.currentQuestionNumber = 1;
                LoadedQuiz.created_by = "AI";

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject q = jsonArray.getJSONObject(i);
                    LoadedQuiz.questions.add(q.getString("question"));

                    JSONArray answers = q.getJSONArray("answers");
                    for (int j = 0; j < answers.length(); j++) {
                        LoadedQuiz.answers.add(answers.getString(j));
                    }


                    LoadedQuiz.correctAnswers.add(q.getString("correct"));
                }

                Platform.runLater(() -> {
                    try {
                        SceneSwitcher.switchScene(event, "QuizQuestionScene.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Error", "Failed to generate quiz."));
            }
        }).start();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }





    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event,"HomePageScene.fxml");
    }

}
