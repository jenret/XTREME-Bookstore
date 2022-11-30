package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.ReceiptModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public ArrayList<ReceiptModel> getAllReceipts(){
        String sql = "SELECT * FROM receipts_view";
        ArrayList<ReceiptModel> receipts = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                receipts.add(new ReceiptModel(rs.getInt(1)
                        ,rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getTimestamp(9).toLocalDateTime()));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return receipts;
    }

}
