package com.example.scarlet.firebase;

/**
 * Created by Scarlet on 31.01.2018.
 */

public class User {

    private int id;
    private int number;
    private String email;

    public User(int id, int number, String email) {
        this.id = id;
        this.number = number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
