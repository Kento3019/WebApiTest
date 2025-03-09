package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dto.Task;

public class TaskDAO {

    private static final String SELECT_TASK = "SELECT ID, USER_ID, TITLE, STATUS, CREATED_AT FROM TASKS";

    // タスク全件取得
    public Optional<List<Task>> getAllTasks() {
        List<Task> taskList = new ArrayList<>();

        // SQL接続・リソース管理はtry-with-resourcesで自動管理
        try (Connection con = JDBCAccessor.getConnection();
                PreparedStatement pStmt = con.prepareStatement(SELECT_TASK);
                ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("ID"), // カラム名を使用
                        rs.getInt("USER_ID"), // ユーザーID
                        rs.getString("TITLE"), // タイトル
                        rs.getString("STATUS"), // ステータス
                        rs.getString("CREATED_AT") // 作成日時
                );
                taskList.add(task);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return taskList.isEmpty() ? Optional.empty() : Optional.of(taskList);
    }
}
