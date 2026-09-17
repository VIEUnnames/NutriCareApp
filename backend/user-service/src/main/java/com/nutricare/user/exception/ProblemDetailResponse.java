package com.nutricare.user.exception;

import java.time.LocalDateTime;

public class ProblemDetailResponse {
    private String title;
    private int status;
    private String detail;
    private String instance;
    private LocalDateTime timestamp;

    public ProblemDetailResponse(String title, int status, String detail, String instance, LocalDateTime timestamp) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
