package com.webhostapp.demo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Offer {

    @SerializedName("image")
    @Expose
    private String image;
    public Offer(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
