package com.example.wireless;


// constructor, getter setter method for Notepage
public class UploadNote {
    private String aName;
    private String aImageUrl;

    //constructor

    public UploadNote() {
    }

    public UploadNote(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "...";
        }

        aName = name;
        aImageUrl = imageUrl;
    }

    public String getName() {
        return aName;
    }

    public void setName(String name) {
        aName = name;
    }

    public String getImageUrl() {
        return aImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        aImageUrl = imageUrl;
    }
}
