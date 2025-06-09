package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;

public class SignUpScene {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Label wrongInformationLabel;
    @FXML
    private Label passwordMatchLabel;
    @FXML
    private Label usernameLabel;

    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "StartScene.fxml");
    }

    @FXML
    private void signUpButtonClicked(ActionEvent event) throws IOException, SQLException {
        if (allInformationIsCorrect()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            String hashedPassword = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());

            try (
                    Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
                    PreparedStatement statement = connection.prepareStatement(sql)
            ) {
                statement.setString(1, usernameField.getText());
                statement.setString(2, hashedPassword);
                statement.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful!");
                SceneSwitcher.switchScene(event, "StartScene.fxml");

            } catch (SQLException e) {
                wrongInformationLabel.setText("Something went wrong during registration.");
                e.printStackTrace();
            }

        } else {
            wrongInformationLabel.setText("Username already exists, or passwords do not match!");
            passwordMatchLabel.setText("Note! Passwords must be at least 6 characters long!");
        }
    }

    @FXML
    private void repeatPasswordFieldClicked(ActionEvent event) throws SQLException, IOException {
        if (allInformationIsCorrect()) {
            Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(username,password) values(?,?)");
            String hashedPassword = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());


            preparedStatement.setString(1, usernameField.getText());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful!");
            SceneSwitcher.switchScene(event, "StartScene.fxml");
            preparedStatement.close();
            connection.close();
        } else {
            wrongInformationLabel.setVisible(true);
            passwordMatchLabel.setVisible(true);
            usernameLabel.setVisible(true);
            wrongInformationLabel.setText("Username already exists, or passwords do not match! ");
            passwordMatchLabel.setText("Note! Passwords must be at least 6 characters long!");
            usernameLabel.setText("Note! Username must be less than 20 characters long!");
        }
    }

    private boolean isPasswordValid() {
        return passwordField.getText().length() >= 6;
    }


    private boolean checkIfPasswordsMatch() {
        return passwordField.getText().equals(repeatPasswordField.getText());
    }

    private boolean checkIfUsernameExists() throws SQLException {
        String sql = "SELECT 1 FROM users WHERE username = ?";

        try (
                Connection connection = DriverManager.getConnection(MainAPP.url, MainAPP.username, MainAPP.password);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, usernameField.getText());

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }


    private boolean allInformationIsCorrect() throws SQLException {
        return isPasswordValid() && checkIfPasswordsMatch() && !checkIfUsernameExists() && !usernameField.getText().isEmpty() && usernameField.getText().length() < 20;
    }
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
