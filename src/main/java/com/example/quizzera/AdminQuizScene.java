package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminQuizScene implements Initializable {
    @FXML
    private VBox adminQuizVbox;
    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene(event, "HomePageScene.fxml");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminQuizVbox.getChildren().clear();
        try (Connection connection = DriverManager.getConnection(MainAPP.url,MainAPP.username,MainAPP.password)){
            PreparedStatement quizStatement = connection.prepareStatement("SELECT id, title FROM quizzes where created_by = ?");
            quizStatement.setString(1, "admin");
            ResultSet quizRS = quizStatement.executeQuery();
            while (quizRS.next()){
                int quizId = quizRS.getInt("id");
                String quizTitle = quizRS.getString("title");
                Button button = new Button(quizTitle);
                HBox hBox = new HBox(10);
                hBox.setAlignment(Pos.valueOf("CENTER"));
                if(LoadedUser.isAdmin){
                    Button deleteButton = new Button("Delete");
                    hBox.getChildren().add(deleteButton);
                    deleteButton.setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirm Deletion");
                        alert.setHeaderText("Are you sure you want to delete this quiz?");
                        alert.setContentText("Quiz title: " + quizTitle);

                        ButtonType yesButton = new ButtonType("Yes");
                        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(yesButton, noButton);

                        alert.showAndWait().ifPresent(response -> {
                            if (response == yesButton) {
                                deleteQuizFromDatabase(quizId);
                                initialize(url, resourceBundle); // Refresh quiz list
                            }
                        });
                    });
                }
                button.setStyle(".button");
                button.setOnAction(event1 -> {
                    try {
                        QuizScene quizController = SceneSwitcher.switchSceneAndGetController(event1, "QuizScene.fxml");
                        quizController.setQuizData(quizId,quizTitle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                hBox.getChildren().add(button);
                adminQuizVbox.getChildren().add(hBox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteQuizFromDatabase(int quizId) {
        try (Connection connection = DriverManager.getConnection(MainAPP.url,MainAPP.username,MainAPP.password);
        PreparedStatement preparedStatement = connection.prepareStatement("delete  from quizzes where id=?")){
            preparedStatement.setInt(1,quizId);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
