package com.example.doancuoiky2.Test;

import com.example.doancuoiky2.Controller.ClientControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServerMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/doancuoiky2/Client.fxml"));
        ClientControl controller = new ClientControl();
        fxmlLoader.setController(controller);
        stage.setScene(new Scene(fxmlLoader.load()));


        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stage.getScene().getWindow());
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/NewPerson.fxml"))));
        stage.setTitle("EChat");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
