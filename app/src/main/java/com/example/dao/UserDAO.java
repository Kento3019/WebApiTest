package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dto.User;

public class UserDAO {

    private static final String SELECT_USER = "SELECT ID, NAME, EMAIL, CREATED_AT FROM USERS";
    private static final String INSERT_USER = "INSERT INTO USERS (NAME, EMAIL, CREATED_AT) VALUES (?, ?, ?)";

    public Optional<List<User>> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection con = JDBCAccessor.getConnection();
                PreparedStatement pStmt = con.prepareStatement(SELECT_USER);
                ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID"), // カラム名を使う
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("CREATED_AT"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return userList.isEmpty() ? Optional.empty() : Optional.of(userList);
    }

    public boolean addUser(User user) {
        boolean isSuccess = false;

        try (Connection con = JDBCAccessor.getConnection();
                PreparedStatement pStmt = con.prepareStatement(INSERT_USER)) {

            con.setAutoCommit(false);

            pStmt.setString(1, user.getName());
            pStmt.setString(2, user.getEmail());
            pStmt.setString(3, user.getCreatedAt());

            int rowsAffected = pStmt.executeUpdate();

            if (rowsAffected > 0) {
                con.commit();
                isSuccess = true;
            } else {
                con.rollback();
                throw new RuntimeException("Failed to insert user, no rows affected.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            try (Connection con = JDBCAccessor.getConnection()) {
                con.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }

        return isSuccess;

    }
}
