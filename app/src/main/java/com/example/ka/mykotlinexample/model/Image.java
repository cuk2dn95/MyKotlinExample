package com.example.ka.mykotlinexample.model;

import com.orm.SugarRecord;

/**
 * Created by KA on 1/7/2018.
 */

public class Image extends SugarRecord<Image> {
    public Image() {
        super();
    }

    String name,imageBase64;



    public Image(String name, String imageBase64) {
        this.name = name;
        this.imageBase64 = imageBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
