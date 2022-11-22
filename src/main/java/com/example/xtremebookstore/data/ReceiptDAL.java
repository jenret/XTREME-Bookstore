package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.ReceiptModel;

import java.sql.*;

public class ReceiptDAL {

    private static final String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "admin";
    private static final String password = "PRO150db";

    public static void createReceipt(ReceiptModel receiptModel) {
        String insert = "insert into sales (bookID, userID, storeID, salePrice) values (?, ?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(insert);
            pst.setString(1, receiptModel.getBookISBN());
            pst.setInt(2, receiptModel.getUserID());
            pst.setInt(3, receiptModel.getStoreID());
            pst.setDouble(4, receiptModel.getSalePrice());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
