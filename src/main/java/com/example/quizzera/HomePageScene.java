package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageScene implements Initializable {
    @FXML
    private Button leaderboardButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button createQuizButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button adminQuizButton;
    @FXML
    private Button communityQuizButton;
    @FXML
    private ImageView createQuizImageView;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button AIQuizButton;
    private void toolTip(){
        Tooltip leaderboardTip = new Tooltip("Click to view leaderboard");

        leaderboardButton.setTooltip(leaderboardTip);

        Tooltip statsTip = new Tooltip("Click to view your stats");
        statsButton.setTooltip(statsTip);

        Tooltip createQuizTip = new Tooltip("Click to create a quiz");
        createQuizButton.setTooltip(createQuizTip);

        Tooltip logOutTip = new Tooltip("Click to Log Out");
        logOutButton.setTooltip(logOutTip);

        Tooltip adminQuizTip = new Tooltip("Click to choose quizzes created by admin");
        adminQuizButton.setTooltip(adminQuizTip);

        Tooltip communityQuizTip = new Tooltip("Click to choose quizzes created by community");
        communityQuizButton.setTooltip(communityQuizTip);

        Tooltip aiQuizTip = new Tooltip("Click to choose quizzes created by AI");
        AIQuizButton.setTooltip(aiQuizTip);
    }

    @FXML
    private void logOutButtonClicked(ActionEvent event) throws Exception {
        SceneSwitcher.switchScene(event, "StartScene.fxml");
        LoadedUser.resetUser();
    }

    @FXML
    private void yourStatsButtonClicked(ActionEvent event) throws Exception{
        SceneSwitcher.switchScene(event, "YourStatsScene.fxml");
    }

    @FXML
    private void leaderboardButtonClicked(ActionEvent event) throws Exception{
        SceneSwitcher.switchScene(event, "LeaderboardScene.fxml");
    }

    @FXML
    private void createQuizButtonClicked(ActionEvent event) throws Exception{
        SceneSwitcher.switchScene(event, "CreateQuizScene.fxml");
    }

    @FXML
    private void adminQuizButtonClicked(ActionEvent event) throws Exception{
        SceneSwitcher.switchScene(event, "AdminQuizScene.fxml");
    }

    @FXML
    private void communityQuizButtonClicked(ActionEvent event) throws Exception{
        SceneSwitcher.switchScene(event, "CommunityQuizScene.fxml");
    }
    @FXML
    private void AIQuizButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event,"AIQuizScene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(LoadedUser.username);
        toolTip();
        if(!LoadedUser.isAdmin && LoadedUser.quizzesTaken < 20 && LoadedUser.maxScoreTakenTimes < 10){
            createQuizButton.setVisible(false);
            createQuizImageView.setVisible(false);
        }
    }
}
