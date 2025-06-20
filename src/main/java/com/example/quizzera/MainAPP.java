package com.example.quizzera;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAPP extends Application {
    public static int red = 255;
    public static int green = 196;
    public static int blue = 0;
    public static String url = "jdbc:mysql://35.234.117.203/quizzera?useSSL=false&serverTimezone=UTC";
    public static String username = "root";
    public static String password = "password123";

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(MainAPP.class.getResourceAsStream("/images/onlyicon.png")));

        stage.setTitle("Quizzera");
        stage.setResizable(false);
        stage.setMinWidth(850);
        stage.setMinHeight(850);



        AnchorPane root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        root.setBackground(null);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}