package com.example.doancuoiky2.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainAdmin extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader fxmlLoader = new FXMLLoader(MainAdmin.class.getResource("/com/example/doancuoiky2/Admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);

        Image image = new Image("/com/example/doancuoiky2/mercedes.png");
        stage.getIcons().add(image);


        stage.setTitle("ADMIN");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
