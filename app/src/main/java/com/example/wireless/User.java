package com.example.wireless;

public class User {
    public String name;
    public String imageLink;
    public int id;

    public User(String name, String imageLink, int id) {
        this.name = name;
        this.imageLink = imageLink;
        this.id = id;
    }
    public User() {
        name = "";
        imageLink = "";
        id = 0;
    }
}
