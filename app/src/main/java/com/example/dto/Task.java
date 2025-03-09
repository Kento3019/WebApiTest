package com.example.dto;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private int userId;
    private String title;
    private String status;
    private String createdAt;

    // コンストラクタ
    public Task(int id, int userId, String title, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ゲッターとセッター
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TaskDTO{id=" + id + ", userId=" + userId + ", title='" + title + "', status='" + status
                + "', createdAt='" + createdAt + "'}";
    }
}
