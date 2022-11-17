package com.example.xtremebookstore.models;

import java.time.LocalDateTime;
import java.util.Date;

public class BookModel {
    public String ISBN;
    private String title;
    private String author;
    private Date publishDate;
    private String edition;
    private double purchasePrice;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BookModel(String ISBN, String title, String author, Date publishDate, String edition, double purchasePrice) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.edition = edition;
        this.purchasePrice = purchasePrice;
    }

    public BookModel() {
    }
}
