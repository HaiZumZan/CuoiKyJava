package com.example.doancuoiky2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO1 {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/khachhang";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Không thể tải driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu");
            e.printStackTrace();
        }
        return connection;
    }
}
