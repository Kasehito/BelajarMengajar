package com.example.belajarmengajarreal.activities;

public class BlogItem {
    private String title;
    private String description;
    private int imageResourceId;

    public BlogItem(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public BlogItem(String title, String description, String imageUrl) {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
