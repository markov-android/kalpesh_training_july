package com.mobileapps.superfling;

/**
 * Created by Exchange on 10/16/2015.
 */
public class Item {
    int id, image_id, user_id;
    String title, username;

    public Item() {

    }

    public Item(int id, int image_id, int user_id, String username, String title) {

        this.id = id;
        this.image_id = image_id;
        this.user_id = user_id;
        this.title = title;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return image_id;
    }

    public int getUserId() { return this.user_id; }

    public String getUsername() { return username; }

    public String getTitle() { return title; }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageId(int image_id) {
        this.image_id = image_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) { this.username = username; }

    public void setTitle(String title) { this.title = title; }

}
