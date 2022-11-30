package com.example.xtremebookstore.models;

import java.sql.Date;

public class BookModel {
    public String ISBN;
    private String title;
    private int author;
    private int edition;
    private Date publishDate;
    private double purchasePrice;

    public BookModel() {
    }

    public BookModel(String ISBN, String title, int author, Date publishDate, int edition, double purchasePrice) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.edition = edition;
        this.purchasePrice = purchasePrice;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
