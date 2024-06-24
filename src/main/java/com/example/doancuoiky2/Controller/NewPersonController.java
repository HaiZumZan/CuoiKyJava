package com.example.doancuoiky2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewPersonController {
    @FXML
    private TextField txtName;

    @FXML
     public void logInButtonOnAction(ActionEvent event) throws IOException {
        if (!txtName.getText().isEmpty()&&txtName.getText().matches("[A-Za-z0-9]+")){
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/doancuoiky2/Client.fxml"));

            ClientControl controller = new ClientControl();
            controller.setClientName(txtName.getText()); // Set the parameter
            fxmlLoader.setController(controller);

            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.setTitle(txtName.getText());
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(windowEvent -> {
                controller.shutdown();
            });
            primaryStage.show();

            txtName.clear();
        }else{
            new Alert(Alert.AlertType.ERROR, "Vui lòng nhập tên của bạn").show();
        }

    }
}
