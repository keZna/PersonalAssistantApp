package com.example.codetride.personalassistantapp;

/**
 * Created by CodeTride on 2017/10/02.
 */

public class Contact {

    private long id;
    private boolean isMe;
    private String message, name, desc, notify, priority;
    private String dateTime;
    private Long userId;

    public Contact() {
    }

    public Contact(long id, String message, String dateTime) {
        this.id = id;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(long id, String name, String desc, String notify, String dateTime) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.notify = notify;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsme() {
        return isMe;
    }

    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return dateTime;
    }

    public void setDate(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return
                 message + '\'' +
                 dateTime + '\'';
    }
}
