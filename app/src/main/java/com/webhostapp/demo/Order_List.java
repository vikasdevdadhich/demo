package com.webhostapp.demo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_List {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("type")
    @Expose
    private String type;

    public Order_List(int id, String address, String contact, String date, String type) {
        this.id=id;
        this.address=address;
        this.contact=contact;
        this.date=date;
        this.type=type;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String name) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String area) {
        this.contact =contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date =date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
