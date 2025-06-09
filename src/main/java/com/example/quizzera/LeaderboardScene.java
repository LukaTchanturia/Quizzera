package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LeaderboardScene implements Initializable {
    @FXML
    private VBox quizzesTakenVbox;
    @FXML
    private VBox maxScoreVbox;


    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene(event, "HomePageScene.fxml");
    }




    private String getMedalEmoji(int rank) {
        return switch (rank) {
            case 1 -> "🥇 ";
            case 2 -> "🥈 ";
            case 3 -> "🥉 ";
            default -> "";
        };
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password)){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, quizzes_taken FROM users ORDER BY quizzes_taken DESC LIMIT 10");
            ResultSet quizzesTakenRS = preparedStatement.executeQuery();
            int rank = 1;
            while (quizzesTakenRS.next()){
                String username = quizzesTakenRS.getString("username");
                int quizzesTaken = quizzesTakenRS.getInt("quizzes_taken");
                Label label = new Label(rank + ". " + getMedalEmoji(rank) + username + ": " + quizzesTaken);
                label.getStyleClass().add("leaderboard-label");
                quizzesTakenVbox.getChildren().add(label);
                rank++;
            }
            preparedStatement = connection.prepareStatement("SELECT username, max_score_taken_times FROM users ORDER BY max_score_taken_times DESC LIMIT 10");
            ResultSet maxScoreRS = preparedStatement.executeQuery();
            rank = 1;
            while (maxScoreRS.next()){
                String username = maxScoreRS.getString("username");
                int maxScore = maxScoreRS.getInt("max_score_taken_times");
                Label label = new Label(rank + ". " + getMedalEmoji(rank) + username + ": " + maxScore);
                rank++;
                label.getStyleClass().add("leaderboard-label");
                maxScoreVbox.getChildren().add(label);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
