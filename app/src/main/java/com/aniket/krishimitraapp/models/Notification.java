package com.aniket.krishimitraapp.models;

public class Notification {
    private int id;
    private String title;
    private String description;
    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
