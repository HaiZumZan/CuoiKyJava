package com.example.doancuoiky2.Controller;

import com.example.doancuoiky2.DAO.DAO;
import com.example.doancuoiky2.Util.MaHoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable  {
    @FXML
    private ComboBox<String> comboboxBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField usernameTxt;

    private PreparedStatement prepare;
    private Connection connection;
    private ResultSet result;
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Kiểm tra thông tin đăng nhập
    public void login(ActionEvent event) throws IOException {
        String username = usernameTxt.getText();
        String matkhau = passwordTxt.getText();
        String role = comboboxBtn.getValue();

        if (username.isEmpty() || matkhau.isEmpty() || role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin đăng nhập và chọn vai trò");
            alert.showAndWait();
            return;
        }

        try {
            connection = DAO.getConnection();
            prepare = connection.prepareStatement("SELECT * FROM thongtinkh WHERE username = ?");
            prepare.setString(1, username);
            result = prepare.executeQuery();

            if (result.next()) {
                String passNhap = result.getString("matkhau");
                String passMaHoa = MaHoa.toSHA1(matkhau);

                if (!passMaHoa.equals(passNhap)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu không đúng");
                    alert.showAndWait();
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập thành công");
                alert.showAndWait();

                // Chuyển hướng người dùng dựa trên vai trò
                switch (role) {
                    case "Server":
                        root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/Server.fxml"));
                        break;
                    case "Client":
                        root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/Server.fxml"));
                        break;
                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Tên đăng nhập hoặc mật khẩu không đúng");
                alert.showAndWait();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Đã xảy ra lỗi trong quá trình đăng nhập");
            alert.showAndWait();
        }
    }

    //Về lại trang đăng ký
    public void returnSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/FirstSignup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxBtn.getItems().addAll("Server","Client");
    }

}