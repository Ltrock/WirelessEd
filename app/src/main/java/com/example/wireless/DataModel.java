package com.example.wireless;
import java.util.HashMap;
import java.util.Map;

// Constructor for upload/ retrieve note that contains keys on firebase
public class DataModel {
    public String title,desc,photos,key,file;

    public DataModel(){ }

    public DataModel(String title, String desc, String photos, String key, String file) {
        this.title = title;
        this.desc = desc;
        this.photos = photos;
        this.key = key;
        this.file = file;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> r = new HashMap<>();
        r.put("title",title);
        r.put("content",desc);
        r.put("photos",photos);
        r.put("key",key);
        r.put("file",file);
        return r;
    }
}