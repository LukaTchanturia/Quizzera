package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class YourStatsScene implements Initializable {
    @FXML
    private Label yourStatsLabel;
    @FXML
    private Label quizzesTakenLabel;
    @FXML
    private Label maxScoreTakenTimesLabel;
    @FXML
    private Label usernameLabel;

    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene(event, "HomePageScene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizzesTakenLabel.setText(String.valueOf(LoadedUser.quizzesTaken));
        maxScoreTakenTimesLabel.setText(String.valueOf(LoadedUser.maxScoreTakenTimes));
        usernameLabel.setText(LoadedUser.username);


        yourStatsLabel.getStyleClass().add("stats-header-label");
        quizzesTakenLabel.getStyleClass().add("stats-label");
        maxScoreTakenTimesLabel.getStyleClass().add("stats-label");
    }

}
