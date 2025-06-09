package com.example.quizzera;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class SceneSwitcher {
    public static void switchScene(ActionEvent event, String fxmlurl) throws IOException {
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlurl));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
