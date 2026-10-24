package com.taskmanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private String userName;

    private String action;

    private LocalDateTime timestamp;

    public ActivityLog() {
    }

    public ActivityLog(Long taskId, String userName,
                       String action, LocalDateTime timestamp) {
        this.taskId = taskId;
        this.userName = userName;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}