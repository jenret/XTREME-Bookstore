package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.ReceiptModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReceiptDAL {

    private static final String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "admin";
    private static final String password = "PRO150db";

    public static void createReceipt(ReceiptModel receiptModel) {
        String insert = "insert into sales (book, user, store, salePrice, timeOfSale) values (?, ?, ?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
