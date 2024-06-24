package com.example.doancuoiky2.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainGuest extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGuest.class.getResource("/com/example/doancuoiky2/KhachHang.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1200,700);
        Image image = new Image("/com/example/doancuoiky2/mercedes.png");
        stage.getIcons().add(image);
        stage.setTitle("GUEST");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
