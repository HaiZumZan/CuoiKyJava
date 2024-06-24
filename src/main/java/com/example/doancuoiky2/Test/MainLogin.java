package com.example.doancuoiky2.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainLogin extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainLogin.class.getResource("/com/example/doancuoiky2/FirstLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,500);
        Image image = new Image("/com/example/doancuoiky2/mercedes.png");
        stage.getIcons().add(image);
        stage.setTitle("LOGIN FORM");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }
}
