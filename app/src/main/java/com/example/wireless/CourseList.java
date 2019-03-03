package com.example.wireless;

public class CourseList {
    public String ChapName;
    public String Img;
    public String Desc; // How difficult that chap is


    public CourseList(){

    }
    public CourseList(String ChapName,String Img, String Desc){
        this.ChapName = ChapName;
        this.Img = Img;
        this.Desc = Desc;
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc){this.Desc = Desc;}


    

}

