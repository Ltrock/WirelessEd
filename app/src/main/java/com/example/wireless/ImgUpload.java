package com.example.wireless;

public class ImgUpload {
    public String imageName;

    public String imageURL;

    public ImgUpload() {

    }

    public ImgUpload(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }


}
