package com.android4dev.navigationview;

/**
 * Created by Exchange on 8/10/2015.
 */
class Item {
    String id;
    String title;
    String price;
    String brand;
    String imageurl;

    Item(String id, String title, String price, String brand, String imageurl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.imageurl = imageurl;
    }
}
