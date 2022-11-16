package com.example.xtremebookstore.models;

import java.time.LocalDateTime;
import java.util.Date;

public class BookModel {
    public int ISBN;
    private String title;
    private String author;
    private LocalDateTime publishDate;
    private String edition;
    private double purchasePrice;

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
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

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
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

    public BookModel(int ISBN, String title, String author, LocalDateTime publishDate, String edition, double purchasePrice) {
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
