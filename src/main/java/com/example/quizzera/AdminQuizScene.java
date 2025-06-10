package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        try (Connection connection = DriverManager.getConnection(MainAPP.url,MainAPP.username,MainAPP.password)){
            PreparedStatement quizStatement = connection.prepareStatement("SELECT id, title FROM quizzes where created_by = ?");
            quizStatement.setString(1, "admin");
            ResultSet quizRS = quizStatement.executeQuery();
            while (quizRS.next()){
                Button button = new Button(quizRS.getString("title")+ quizRS.getInt("id"));
                button.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #e6e6e6);"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 14px;"
                        + "-fx-text-fill: #333;"
                        + "-fx-background-radius: 10;");
                adminQuizVbox.getChildren().add(button);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
