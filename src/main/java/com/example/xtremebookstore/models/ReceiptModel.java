package com.example.xtremebookstore.models;

import java.time.LocalDateTime;

public class ReceiptModel {

    private int id;
    private String bookISBN;
    private String title;
    private int userID;
    private String username;
    private int storeID;
    private String storeName;
    private double salePrice;
    private LocalDateTime timeOfSale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(LocalDateTime timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public ReceiptModel() {}

    public ReceiptModel(int id, String bookISBN, String title, int userID, String username, int storeID, String storeName, double salePrice, LocalDateTime timeOfSale) {
        this.id = id;
        this.bookISBN = bookISBN;
        this.title = title;
        this.userID = userID;
        this.username = username;
        this.storeID = storeID;
        this.storeName = storeName;
        this.salePrice = salePrice;
        this.timeOfSale = timeOfSale;
    }

}
