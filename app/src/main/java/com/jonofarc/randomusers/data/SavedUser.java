package com.jonofarc.randomusers.data;

import android.graphics.Bitmap;

/**
 * Created by Jonathan Maldonado on 11/2/2017.
 */

public class SavedUser {


    Bitmap bmp;
    String name;
    String email;
    String address;
    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Bitmap getBmp() {
        return bmp;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
        // etc

    /*
    List<Bitmap> bmp;
    List <String> name;
    List <String> email;
    List <String> address;
    public void setBmp(List<Bitmap> bmp) {
        this.bmp = bmp;
    }
    public void setName(List <String> name) {
        this.name = name;
    }
    public void setEmail(List <String> email) {
        this.email = email;
    }
    public void setAddress(List <String> address) {
        this.address = address;
    }
    */
}
