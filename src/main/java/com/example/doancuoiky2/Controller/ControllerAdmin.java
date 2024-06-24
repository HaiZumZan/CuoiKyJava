package com.example.doancuoiky2.Controller;

import com.example.doancuoiky2.DAO.DAO1;
import com.example.doancuoiky2.Model.Manage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAdmin {

    @FXML
    private Button add_btn;

    @FXML
    private TableColumn<Manage, String> bienso_col;

    @FXML
    private Button delete_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private TableColumn<Manage, Double> id_col;

    @FXML
    private TableColumn<Manage, String> loaixe_col;

    @FXML
    private TableColumn<Manage, String> makh_col;

    @FXML
    private TableColumn<Manage, String> name_col;

    @FXML
    private TableView<Manage> quanly;

    @FXML
    private TextField bienso_txt;

    @FXML
    private TextField loaixe_txt;

    @FXML
    private TextField makh_txt;

    @FXML
    private TextField ten_txt;

    private PreparedStatement prepare;
    private Connection connection;
    private ResultSet result;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("tenkhach"));
        loaixe_col.setCellValueFactory(new PropertyValueFactory<>("loaixe"));
        bienso_col.setCellValueFactory(new PropertyValueFactory<>("bienso"));
        makh_col.setCellValueFactory(new PropertyValueFactory<>("makh"));
        quanly.setItems(getManage());

        // Add event handlers
        add_btn.setOnAction(e -> addUser());
        delete_btn.setOnAction(e -> deleteUser());
        edit_btn.setOnAction(e -> editUser());
    }


    public ObservableList<Manage> getManage() {
        ObservableList<Manage> list = FXCollections.observableArrayList();
        try {
            connection = DAO1.getConnection();
            prepare = connection.prepareStatement("SELECT * FROM khachdatxe");
            result = prepare.executeQuery();
            while (result.next()) {
                list.add(new Manage(result.getInt("id"), result.getString("tenkhach"), result.getString("loaixe"), result.getString("bienso"), result.getString("makh")));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    return list;
        }

        //Them user
        public void addUser() {
            String makh = makh_txt.getText();
            String tenkhach = ten_txt.getText();
            String loaixe = loaixe_txt.getText();
            String bienso = bienso_txt.getText();

            try {
                connection = DAO1.getConnection();
                prepare = connection.prepareStatement("INSERT INTO khachdatxe(makh, tenkhach, loaixe, bienso) VALUES (?, ?, ?, ?)");
                prepare.setString(1, makh);
                prepare.setString(2, tenkhach);
                prepare.setString(3, loaixe);
                prepare.setString(4, bienso);

                prepare.executeUpdate();
                quanly.setItems(getManage());
                } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Xoa user
        public void deleteUser(){
            Manage selected = quanly.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng chọn một hàng để xóa");
                alert.showAndWait();
                return;
            }
            try {
                connection = DAO1.getConnection();
                prepare = connection.prepareStatement("DELETE FROM khachdatxe WHERE id = ?");
                prepare.setInt(1, selected.getId());
                prepare.executeUpdate();
                quanly.setItems(getManage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void editUser(){
        Manage selected = quanly.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng chọn một hàng để sửa");
                alert.showAndWait();
                return;
            }
            List<String> fields = new ArrayList<>(Arrays.asList("makh", "tenkhach", "loaixe", "bienso"));
            List<String> values = new ArrayList<>(Arrays.asList("KH001", "New User", "Car", "123ABC"));
            try {
                connection = DAO1.getConnection();
                StringBuilder query = new StringBuilder("UPDATE khachdatxe SET ");
                for (int i = 0; i < fields.size(); i++) {
                    query.append(fields.get(i)).append(" = ?");
                    if (i < fields.size() - 1) {
                        query.append(", ");
                    }
                }
                query.append(" WHERE id = ?");
                prepare = connection.prepareStatement(query.toString());
                for (int i = 0; i < values.size(); i++) {
                    prepare.setString(i + 1, values.get(i));
                }
                prepare.setInt(values.size() + 1, selected.getId());
                prepare.executeUpdate();
                quanly.setItems(getManage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
