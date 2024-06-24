package com.example.doancuoiky2.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSignUp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSignUp.class.getResource("/com/example/doancuoiky2/FirstSignup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),940,520);

        Image image = new Image("/com/example/doancuoiky2/mercedes.png");
        stage.getIcons().add(image);
        stage.setTitle("SIGNUP FORM");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
    }

