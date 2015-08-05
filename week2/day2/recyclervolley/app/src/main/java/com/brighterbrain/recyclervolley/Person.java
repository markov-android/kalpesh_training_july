package com.brighterbrain.recyclervolley;

/**
 * Created by Exchange on 8/4/2015.
 */
class Person {
    String first_name;
    String surname;
    String address;
    String phone_number;
    String email;
    String id;
    String createdAt;
    String updatedAt;

    Person(String name, String surname) {
        this.first_name = name;
        this.surname = surname;
    }
}
