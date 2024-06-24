package com.example.doancuoiky2.Controller;

import com.example.doancuoiky2.DAO.DAO;
import com.example.doancuoiky2.Util.MaHoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ControllerSignUp {


    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField emailText;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField phoneText;

    @FXML
    private Button regisBtn;

    @FXML
    private TextField usernameText;

    private Stage stage;
    private Scene scene;
    private Parent root;


public void switchLogin(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/FirstLogin.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
private PreparedStatement prepare;
private Connection connection;
private ResultSet result;
public void signup(ActionEvent event) throws IOException {
    String username = usernameText.getText();
    String email = emailText.getText();
    String sdt = phoneText.getText();
    String matkhau = passwordText.getText();
    if (username.isEmpty()||email.isEmpty()||sdt.isEmpty()||matkhau.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Vui lòng điền đầy đủ thông tin vào các trường");
        alert.showAndWait();

    }else {
        try{
            matkhau = MaHoa.toSHA1(matkhau);
            connection = DAO.getConnection();
            prepare  = connection.prepareStatement("insert into thongtinkh(username,email,sdt,matkhau) values (?,?,?,?)");
            prepare.setString(1,username);
            prepare.setString(2,email);
            prepare.setString(3,sdt);
            prepare.setString(4,matkhau);
           prepare.executeUpdate();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Đăng ký thành công");
           alert.showAndWait();
           root=FXMLLoader.load(getClass().getResource("/com/example/doancuoiky2/FirstLogin.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene= new Scene(root);
           stage.setScene(scene);
           stage.show();
            }catch (Exception e){
            e.printStackTrace();
        }
    }

}




}

