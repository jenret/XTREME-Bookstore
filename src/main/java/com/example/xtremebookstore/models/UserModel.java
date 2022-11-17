package com.example.xtremebookstore.models;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String role;
    private String storeID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public UserModel(int id, String username, String password, String role, String storeID) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.storeID = storeID;
    }

    public UserModel() {
    }
}
