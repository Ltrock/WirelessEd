package com.example.wireless;

public class CourseList {
    public String ChapName;
    public String Img;
    public float ChapRating; // How difficult that chap is


    public CourseList(){

    }
    public CourseList(String ChapName,String Img){
        this.ChapName = ChapName;
        this.Img = Img;
    }

    public String getChapName() {
        return ChapName;
    }

    public void setChapName(String ChapName) {
        this.ChapName = ChapName;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
    public float getChapRating() {
        return ChapRating;
    }

    public void setChapRating(float ChapRating) {
        this.ChapRating = ChapRating;
    }


}

