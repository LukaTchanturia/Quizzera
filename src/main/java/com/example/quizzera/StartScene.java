package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;


public class StartScene {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label wrongInformationLabel;


    @FXML
    private void signUpButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "SignUpScene.fxml");
    }

    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException, SQLException {
        if (checkIfAllInformationIsCorrect()) {
            String sql = """
                    SELECT id, is_admin, password, quizzes_taken, max_score_taken_times
                    FROM users
                    WHERE username = ?
                    """;

            try (
                    Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, usernameField.getText());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        LoadedUser.id = resultSet.getInt("id");
                        LoadedUser.username = usernameField.getText();
                        LoadedUser.password = resultSet.getString("password");
                        LoadedUser.isAdmin = resultSet.getBoolean("is_admin");
                        LoadedUser.quizzesTaken = resultSet.getInt("quizzes_taken");
                        LoadedUser.maxScoreTakenTimes = resultSet.getInt("max_score_taken_times");

                        SceneSwitcher.switchScene(event, "HomePageScene.fxml");
                    } else {
                        wrongInformationLabel.setVisible(true);
                        wrongInformationLabel.setText("Something went wrong loading the user.");
                    }
                }
            }
        } else {
            wrongInformationLabel.setVisible(true);
            wrongInformationLabel.setText("Wrong username or password!");
        }
    }

    private boolean checkIfAllInformationIsCorrect() throws IOException, SQLException {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (
                Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, usernameField.getText());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String dbPassword = resultSet.getString("password");
                    return BCrypt.checkpw(passwordField.getText(), dbPassword);
                } else {
                    return false;
                }
            }
        }
    }
}
