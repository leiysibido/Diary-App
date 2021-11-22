package com.example.dailydairyonline;

public class UserModel {

    String Title;
    String Description;
    String image;

    public UserModel() {
    }

    public UserModel(String title, String description, String image) {
        Title = title;
        Description = description;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
