package com.example.photographerbooking.model;

import java.time.LocalDateTime;

public class Notification {
    private long id;
    private String content;
    private String createSrcId;
    private LocalDateTime createTime;

    public Notification(long id, String content, String createSrcId, LocalDateTime createTime) {
        this.id = id;
        this.content = content;
        this.createSrcId = createSrcId;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateSrcId() {
        return createSrcId;
    }

    public void setCreateSrcId(String createSrcId) {
        this.createSrcId = createSrcId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
