package com.example.customlistviewsample;

import android.graphics.drawable.Drawable;

public class ProfileItem {
    private Drawable icon;
    private String name;
    private String message;

    public ProfileItem(){

    }

    public ProfileItem(Drawable icon,
                       String name,
                       String message){
        this.icon = icon;
        this.name = name;
        this.message = message;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
