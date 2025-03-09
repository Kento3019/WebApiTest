package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCAccessor {
    public static Connection getConnection() throws Exception {

        ResourceBundle resource = ResourceBundle.getBundle("properties.config");
        try {
            // データベース接続
            String url = resource.getString("JDBC_URL");
            String user = resource.getString("JDBC_USER");
            String password = resource.getString("JDBC_PASSWORD");

            if (url == null || user == null || password == null) {
                throw new IllegalArgumentException("プロパティファイルに必要な値が不足しています");
            }

            // 接続を返す
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.err.println("データベース接続中にエラーが発生しました: " + e.getMessage());
            throw e;
        }
    }
}
