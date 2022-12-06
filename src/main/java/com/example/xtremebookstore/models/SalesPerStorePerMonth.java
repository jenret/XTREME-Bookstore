package com.example.xtremebookstore.models;

public class SalesPerStorePerMonth {

    private String storeName;
    private int month;
    private double salesTotal;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(double salesTotal) {
        this.salesTotal = salesTotal;
    }

    public SalesPerStorePerMonth() {}

    public SalesPerStorePerMonth(String storeName, int month, double salesTotal) {
        this.storeName = storeName;
        this.month = month;
        this.salesTotal = salesTotal;
    }
}
