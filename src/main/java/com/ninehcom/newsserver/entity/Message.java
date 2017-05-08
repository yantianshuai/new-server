package com.ninehcom.newsserver.entity;

import java.util.Date;

/**
 * Created by liufx on 15/12/7.
 */
public class Message {

    private Long id;
    private String type;
    private Long targetId;
    private String content;
    private String userId;
    private String fromUserId;
    private Date createdAt;
    private Date updatedAt;

    public Message() {
    }

    public Message(Long id, String type, Long targetId, String content, String userId, String fromUserId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.type = type;
        this.targetId = targetId;
        this.content = content;
        this.userId = userId;
        this.fromUserId = fromUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
